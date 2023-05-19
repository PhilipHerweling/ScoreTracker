package com.philip.st.leagues;

//Imports for Jsoup library and ObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philip.st.Constructors.Table;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class PremierLeague {
    public static String jsonDataPremTable;

    @PostConstruct
    public static void premTableWebScrape() {

        //Creating an array called premTable for my PremTable class
        ArrayList<Table> table = new ArrayList<>();

        //Storing website URLs I plan on web scrapping to get data for my web application
        final String url = "https://www.premierleague.com/tables";

        //Try catch block: this is where I am going to write my code to scrape data from the websites above
        try {

            //Creating a document called premTable which is going to store all the data the url i entered
            //The .cookie() function allows me to consent to cookies on the webpage allowing me to gain access
            //To the data I need
            final Document premTableDoc = Jsoup.connect(url).cookie("CONSENT", "YES+1").get();

            //Testing purposes only
            //System.out.println(premTable.getElementsByTag("table"));

            //for loop loops through all the rows in the given table
            for (Element row : premTableDoc.select(
                    ".isPL.tableBodyContainer tr"
            )) {

                //.team for both long and short name, .short for short name, .long for long name of teams
                // if else statement is needed in case the first fow is empty from research ive done many tables
                // have a blank row right at the start which we don't want to sort
                if (row.select(".team").text().equals("")){
                    continue;

                } else{
                    //Creating a String with all the position numbers from 1 to 20
                    final String premPositions = row.select(".value").text();
                    //System.out.println(premPositions);

                    //Creating a Strings with all the data I need for my website
                    final String teamName = row.select(".long").text();
                    final String gamesPlayed = row.select("td:nth-of-type(4)").text();
                    final String wins = row.select("td:nth-of-type(5)").text();
                    final String draws = row.select("td:nth-of-type(6)").text();
                    final String losses = row.select("td:nth-of-type(7)").text();
                    final String goalDiff = row.select("td:nth-of-type(10)").text();
                    final String points = row.select(".points").text();

                    //Saving all teams data into the array premTable
                    Table team = new Table(premPositions, teamName, gamesPlayed, wins, draws, losses, goalDiff, points);
                    table.add(team);


                    //Testing to see if all the strings work and got the correct data
                    //System.out.println(premPositions + " " + teamName + " " + gamesPlayed + " " + wins + " " + draws + " " + losses + " " + goalDiff + " " + points);
                }
            }

            //Creating an object mapper to convert my java String into JSON array
            ObjectMapper mapper = new ObjectMapper();

            //converting premTable array in a Json array and storing it
            jsonDataPremTable = mapper.writeValueAsString(table);

            //Testing to see if my mapper successfully converted the java strings into a JSON array
            //System.out.println(jsonDataPremTable);

        }catch (Exception ex){
            //Prints out the exception if there is one
            System.out.println(ex);

        }


    }// End of premTableWebScrape method




}

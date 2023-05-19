package com.philip.st.leagues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philip.st.Constructors.Table;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Ligue1 {

    public static String jsonDataL1Table;

    @PostConstruct
    public static void ligue1WebScrape() {

        final String url = "https://www.skysports.com/ligue-1-table";
        ArrayList<Table> table = new ArrayList<>();


        try{

            //Creating a document called l1TableDoc which is going to store all the data the url i entered
            //The .cookie() function allows me to consent to cookies on the webpage allowing me to gain access
            //To the data I need
            final Document l1TableDoc = Jsoup.connect(url).cookie("CONSENT", "YES+1").get();

            //for loop loops through all the rows in the given table
            for (Element row : l1TableDoc.select(
                    ".standing-table__table tr"
            )) {

                // if else statement is needed in case the first fow is empty from research ive done many tables
                // have a blank row right at the start which we don't want to sort
                if (row.select(".standing-table__cell").text().equals("")) {
                    continue;
                } else {

                    //Scraping and storing the data i need in to Strings
                    final String positions = row.select("td:nth-of-type(1)").text();
                    final String teamName = row.select("td:nth-of-type(2)").text();
                    final String gamesPlayed = row.select("td:nth-of-type(3)").text();
                    final String wins = row.select("td:nth-of-type(4)").text();
                    final String draws = row.select("td:nth-of-type(5)").text();
                    final String losses = row.select("td:nth-of-type(6)").text();
                    final String goalDiff = row.select("td:nth-of-type(9)").text();
                    final String points = row.select("td:nth-of-type(10)").text();


                    //Testing to see if all the strings work and got the correct data
                    //System.out.println(positions + " " + teamName + " " + gamesPlayed + " " + wins + " " + draws + " " + losses + " " + goalDiff + " " + points);

                    //If statement ensures no items get past into the JSON mapper which are = null
                    if(!positions.equals("")){
                        //Saving all teams data into the array
                        Table team = new Table(positions, teamName, gamesPlayed, wins, draws, losses, goalDiff, points);
                        table.add(team);
                    }

                }//end of else

            }//end of for loop

            //Creating an object mapper to convert my java String into JSON array
            ObjectMapper mapper = new ObjectMapper();

            //converting table array in a Json array and storing it
            jsonDataL1Table = mapper.writeValueAsString(table);

            //Testing to see if my mapper successfully converted the java strings into a JSON array
            //System.out.println(jsonDataL1Table);



        }catch(Exception ex){

            System.out.println(ex);

        }

    }//End of method

}//End of class

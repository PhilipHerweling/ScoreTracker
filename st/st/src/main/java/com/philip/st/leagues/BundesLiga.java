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
public class BundesLiga {

    public static String jsonDataBLTable;

    @PostConstruct
    public static void bundesLWebScrape() {

        final String url = "https://www.bundesliga.com/en/bundesliga/table";

        ArrayList<Table> table = new ArrayList<>();

        try{

            //Creating a document called blTableDoc which is going to store all the data the url i entered
            //The .cookie() function allows me to consent to cookies on the webpage allowing me to gain access
            //To the data I need
            final Document blTableDoc = Jsoup.connect(url).cookie("CONSENT", "YES+1").get();

            //for loop loops through all the rows in the given table
            for (Element row : blTableDoc.select(
                    ".ng-star-inserted tr"
            )) {

                // if else statement is needed in case the first fow is empty from research ive done many tables
                // have a blank row right at the start which we don't want to sort
                if (row.select(".ng-star-inserted").text().equals("")){
                    continue;

                } else{

                    //rank
                    //Scraping and storing the data i need in to Strings
                    final String positions = row.select("td.rank").text();
                    final String teamName = row.select(".d-none.d-480-inline-block.d-576-none").text();
                    final String gamesPlayed = row.select("td.matches").text();
                    final String wins = row.select("td.wins").text();
                    final String draws = row.select("td.draws").text();
                    final String losses = row.select("td.losses").text();
                    String goalDiff = row.select("td.goals").text();
                    final String points = row.select("td.pts").text();

                    //Working out the goal difference
                    String[] split = goalDiff.split(":");
                    int scored = Integer.parseInt(split[0]);
                    int con = Integer.parseInt(split[1]);
                    int sum = scored - con;
                    goalDiff = String.valueOf(sum);
                    //System.out.println(goalDiff);

                    //Testing to see if all the strings work and got the correct data
                    //System.out.println(positions + " " + teamName + " " + gamesPlayed + " " + wins + " " + draws + " " + losses + " " + goalDiff + " " + points);

                    Table team = new Table(positions, teamName, gamesPlayed, wins, draws, losses, goalDiff, points);
                    table.add(team);


                }

            }//End of for loop

            //Creating an object mapper to convert my java String into JSON array
            ObjectMapper mapper = new ObjectMapper();

            //converting table array in a Json array and storing it
            jsonDataBLTable = mapper.writeValueAsString(table);

            //Testing to see if my mapper successfully converted the java strings into a JSON array
            //System.out.println(jsonDataBLTable);




        }catch (Exception ex){
            System.out.println(ex);
        }


    }//End of Method

}//End of Class

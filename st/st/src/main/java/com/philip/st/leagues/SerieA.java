package com.philip.st.leagues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philip.st.Constructors.Table;
import jakarta.annotation.PostConstruct;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class SerieA {

    public static String jsonDataSATable;

    @PostConstruct
    public static void serieATableWebScrape() {

        ArrayList<Table> table = new ArrayList<>();

        //Storing website URLs I plan on web scrapping to get data for my web application
        final String url = "https://www.theguardian.com/football/serieafootball/table";

        //Try catch block: this is where I am going to write my code to scrape data from the websites above
        try {

            //Storing the three cookies on the site and excepting them later
            Map<String, String> cookies = new HashMap<>();
            cookies.put("_id1", "5f8849a51f65495a4d816b47");
            cookies.put("_id2", "5f8849a51f65495a4d816c28");
            cookies.put("_id3", "5f8849a61f65495a4d81723a");

            Connection connection = Jsoup.connect(url);
            connection.header("Cookie", formatCookies(cookies));



            //Creating a document called serieATable which is going to store all the data the url i entered
            //The .cookie() function allows me to consent to cookies on the webpage allowing me to gain access
            //To the data I need
            final Document serieATableDoc = connection.get();

            //Testing purposes only
            //System.out.println(serieATableDoc.getElementsByTag("table"));

            for (Element row : serieATableDoc.select(
                    "table.table--football.table--league-table.table--responsive-font.table--striped tr"
            )){

                if (row.select(".table-column--sub").text().equals("") || row.select(".table-column--sub").text().equals("P")) {
                    continue;
                }else {

                    //Creating a Strings with all the data I need for my website
                    final String position = row.select(".table-column--sub").text();
                    final String teamName = row.select(".table-column--main").text();
                    final String gamesPlayed = row.select("td:nth-of-type(3)").text();
                    final String wins = row.select("td:nth-of-type(4)").text();
                    final String draws = row.select("td:nth-of-type(5)").text();
                    final String losses = row.select("td:nth-of-type(6)").text();
                    final String goalDiff = row.select("td:nth-of-type(9)").text();
                    final String points = row.select("td:nth-of-type(10)").text();

                    //Testing to see if all the strings work and got the correct data
                    //System.out.println(position + " " + teamName + " " + gamesPlayed + " " + wins + " " + draws + " " + losses + " " + goalDiff + " " + points);

                    Table team = new Table(position, teamName, gamesPlayed, wins, draws, losses, goalDiff, points);
                    table.add(team);


                }


            }//End of For Loop

            //Creating an object mapper to convert my java String into JSON array
            ObjectMapper mapper = new ObjectMapper();

            //converting table array in a Json array and storing it
            jsonDataSATable = mapper.writeValueAsString(table);

            //Testing to see if my mapper successfully converted the java strings into a JSON array
            //System.out.println(jsonDataSATable);

        }catch (Exception ex){
            System.out.println(ex);
        }


    }//End of method

    private static String formatCookies(Map<String, String> cookies) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

}//End of class

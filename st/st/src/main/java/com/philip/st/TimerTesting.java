package com.philip.st;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

import static com.philip.st.leagues.BundesLiga.bundesLWebScrape;
import static com.philip.st.leagues.LigaSantander.lsWebScrape;
import static com.philip.st.leagues.Ligue1.ligue1WebScrape;
import static com.philip.st.leagues.PremierLeague.premTableWebScrape;
import static com.philip.st.leagues.SerieA.serieATableWebScrape;


//This class and it's methods allows the program to rerun the 5
//web scraping classes in this project every half an hour which
//makes sure the league tables displayed on the client side are up-to date
@Component
public class TimerTesting {

    private Timer timer;

    public TimerTesting(){
        startTimer();
    }

    @PostConstruct
    private void startTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scrapeWebData();
            }
        },0, 1800000); // time is in milliseconds and is equal to half an hour


    }

    @PostConstruct
    private void stopTimer(){
        timer.cancel();
    }


    //This method gets called in the startTimer method
    //And just reruns the web scraping methods which I use to make my league tables
    //every half an hour asynchronously
    @PostConstruct
    private void scrapeWebData(){
        //System.out.println("test");
        premTableWebScrape();
        ligue1WebScrape();
        serieATableWebScrape();
        lsWebScrape();
        bundesLWebScrape();
    }

}

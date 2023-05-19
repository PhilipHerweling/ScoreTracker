package com.philip.st.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.philip.st.leagues.PremierLeague.jsonDataPremTable;

@RestController
public class PLTController {

    //This Controller class and method is an API end-point
    //This method here makes the string variable with the JSON data available to be GET Requested
    //by my javaScript file which then takes this scraped data and populates my Premier League Table
    @GetMapping("/plt")
    @CrossOrigin(origins = "http://localhost:3000")
    public String pltJsonData(){
        return jsonDataPremTable;
    }

}

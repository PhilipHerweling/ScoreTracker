package com.philip.st.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.philip.st.leagues.SerieA.jsonDataSATable;

@RestController
public class SATController {

    //This Controller class and method is an API end-point
    //This method here makes the string variable with the JSON data available to be GET Requested
    //by my javaScript file which then takes this scraped data and populates my Serie A Table
    @GetMapping("/sat")
    @CrossOrigin(origins = "http://localhost:3000")
    public String SATJsonData(){
        return jsonDataSATable;
    }

}

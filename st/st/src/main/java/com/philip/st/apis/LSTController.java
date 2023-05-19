package com.philip.st.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.philip.st.leagues.LigaSantander.jsonDataLSTable;

@RestController
public class LSTController {

    @GetMapping("/lst")
    @CrossOrigin(origins = "http://localhost:3000")
    public String pltJsonData(){
        return jsonDataLSTable;
    }

}

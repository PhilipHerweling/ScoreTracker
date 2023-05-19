package com.philip.st.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.philip.st.leagues.Ligue1.jsonDataL1Table;

@RestController
public class L1TController {

    @GetMapping("/l1t")
    @CrossOrigin(origins = "http://localhost:3000")
    public String l1tJsonData(){
        return jsonDataL1Table;
    }

}

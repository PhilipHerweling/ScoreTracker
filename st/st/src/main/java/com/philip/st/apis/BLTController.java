package com.philip.st.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.philip.st.leagues.BundesLiga.jsonDataBLTable;

@RestController
public class BLTController {

    @GetMapping("/blt")
    @CrossOrigin(origins = "http://localhost:3000")
    public String bltJsonData(){
        return jsonDataBLTable;
    }

}

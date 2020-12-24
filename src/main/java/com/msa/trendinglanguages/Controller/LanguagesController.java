package com.msa.trendinglanguages.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguagesController {
    
    @GetMapping(value="/languages")
    String getLanguages(){

        return "This is to display all languages" ;

    }
}

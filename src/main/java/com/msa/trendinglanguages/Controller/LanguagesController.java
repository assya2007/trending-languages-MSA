package com.msa.trendinglanguages.Controller;


import com.msa.trendinglanguages.model.Data;
import com.msa.trendinglanguages.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LanguagesController {
    @Autowired
    RestTemplate restTemplate ;

    @Value("${github.url}")
    private String gitHubUrl;

    @GetMapping(value="/languages")
    public List<Item>  getLanguages(){

        //calculate  the date before 30 days from now
        LocalDateTime date = LocalDateTime.now().minusDays(30);
        //format the this date to the format yyyy-MM-dd'T'HH:mm:ss
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        //call the hitHub API to retrieve the  trending public repos created after this date
        Data response = restTemplate.getForObject(gitHubUrl.replace("createdDate",formattedDate), Data.class);
        //get just the first 100 repos in the result
        List<Item> trendingRepo = response.getItems()
                .stream()
                .limit(100)
                .collect(Collectors.toList());
        
        return trendingRepo ;

    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

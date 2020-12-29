package com.msa.trendinglanguages.Controller;


import com.msa.trendinglanguages.model.Data;
import com.msa.trendinglanguages.model.Item;
import com.msa.trendinglanguages.model.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LanguagesController {
    @Autowired
    RestTemplate restTemplate ;

    @Value("${github.url}")
    private String gitHubUrl;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(LanguagesController.class);

    @GetMapping(value="/languages")
    public List<ResultData> getLanguages() throws Exception {

        // List<>
        List<ResultData>  resultDataList = new ArrayList<ResultData>();

        try {
            //calculate  the date before 30 days from now
            LocalDateTime date = LocalDateTime.now().minusDays(30);
            //format the this date to the format yyyy-MM-dd'T'HH:mm:ss
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            //call the hitHub API to retrieve the  trending public repos created after this date
            LOGGER.info(" <<< start getting the gitHub API to retrieve the  trending public repos created after  : {} ", formattedDate);
            Data response = restTemplate.getForObject(gitHubUrl.replace("createdDate",formattedDate), Data.class);

            if (response == null) {
                LOGGER.error("error while calling the gitHub search API");
                throw new Exception("error while calling the gitHub search API");
            } else if (response.getItems() == null) {
                LOGGER.error("the response has got no items");
                throw new Exception("the response has got no items");
            } else {

                //if response well returned  get just the first 100 repos in the result and filter repo without language then construct a map of languages and repos :
                Map<String, List<Item>> trendingRepo = restTemplate.getForObject(gitHubUrl.replace("createdDate",formattedDate), Data.class).getItems()
                        .stream()
                        .filter(p -> p.getLanguage() != null)
                        .collect(Collectors.groupingBy(Item::getLanguage)) ;

                //map to a resultDataList
                for (Object key : trendingRepo.keySet()) {
                    ResultData resultData = new ResultData();
                    resultData.setLanguage(key.toString());
                    resultData.setRepoCount(trendingRepo.get(key).size());
                    resultData.setRepoList(trendingRepo.get(key));

                    resultDataList.add(resultData) ;

                }

            }

        } catch (Exception e) {
            LOGGER.error("");
            throw new Exception(e.getMessage());
        }
        LOGGER.info("<<< end getting the gitHub API to retrieve the  trending public repos");

        return resultDataList ;

    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

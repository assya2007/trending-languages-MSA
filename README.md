# Trending-languages-MSA
REST microservice that list the languages used by the first 100 trending public repos on GitHub created during the last 30 days.<br/>

For every language, the miscroservice display:<br/>

  *The number of repos using this language.<br/>
  *The list of repos using the language.<br/>

The gitHub API used here is : [search-repositories](https://docs.github.com/en/free-pro-team@latest/rest/reference/search#search-repositories)
# Technologies
  *SpringBoot v2.4.1<br/>
  *RestTemplate<br/>
  *Java 8<br/>
  *Maven
  *Swagger 2 (documenting the microservice)
  
# Launch
  *Clone the repo in your local machine<br/>
  *Run the porject by TrendinglanguagesApplication class ..<br/>
  *Under [http://localhost:9090/languages](http://localhost:9090/languages)  :<br/> 
         have the list the languages used by the first 100 trending public repos on GitHub created during the last 30 days(from now).<br/>
  *Under [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html) : display the microservice documentation . <br/>
  *The port : 9090 can be changed under resources/application.properties
  


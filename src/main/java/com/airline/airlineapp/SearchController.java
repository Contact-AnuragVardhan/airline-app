package com.airline.airlineapp;

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    // Read parameters from request
    // Call external api with same parameters
    // Get response
    // Parse response
    // Select information
    // Return data to client
    @CrossOrigin
    @RequestMapping("/search")
    public Map method(@RequestParam Map<String, String> allParams) throws Exception {
        System.out.println("Search endpoint" + allParams.entrySet());

        HttpClient client = HttpClient.newBuilder()
        .version(Version.HTTP_1_1)
        .followRedirects(Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(20))
        .build();

        /*String url = "https://tequila-api.kiwi.com/v2/search?"
            +"fly_from="+allParams.get("departFrom")
            +"&fly_to="+allParams.get("arrivingAt")
            +"&date_from="+allParams.get("leavingOn")
            +"&date_to="+allParams.get("leavingOn")
            +"&return_from="+allParams.get("returningOn")
            +"&return_to="+allParams.get("returningOn");*/
        String url = "https://tequila-api.kiwi.com/v2/search?fly_from=LGA&fly_to=MIA&dateFrom=01/04/2022&dateTo=02/04/2022";

        System.out.println("URL " + url);

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(2))
        .header("apikey", "qVSTQ-ycxja9LbMOp3bgIC0UupRFG8S_")
        .GET()
        .build();
  

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        // System.out.println(response.statusCode());
        // System.out.println(response.body());

        Map<String, String> resp = new HashMap<String, String>();

        resp.put("result", response.body());

        return resp;

    }

}

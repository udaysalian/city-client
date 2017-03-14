package com.trifork.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by uday on 3/13/2017.
 */
@RestController
@RequestMapping("/city")
public class CityRestController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    org.springframework.cloud.client.discovery.DiscoveryClient client;

    @GetMapping("/capital")
    public String[] capital(){

        ServiceInstance serviceInstance = client.getInstances("city-service")
                .stream()
                .findFirst()
                .orElseThrow(()-> new RuntimeException("city-service not found"));
        String url = serviceInstance.getUri().toString() + "/rest/city/capital";


    String[] test = null;
        test =  restTemplate.getForObject(url, String[].class);
    return test;



    }
}

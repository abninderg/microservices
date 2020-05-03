package com.microservices.services.configclient.controller;

import com.microservices.services.configclient.model.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

    @Autowired
    private RestTemplate restTemplate;

    private AccountDetails accountDetails;

    @Autowired
    public WebController(final AccountDetails accountDetails){
        this.accountDetails=accountDetails;
    }

    @GetMapping("/api/account/details")
    public String getAccountDetails(){
        String addressDetails = restTemplate.getForObject("http://address-service/address/", String.class);
        return accountDetails + " ==> " + addressDetails;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

package com.example.msjob.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean //springboot needs to manage object sof this class
    @LoadBalanced
    //loadbalanced annot configures the rest template to use loadbalancer client
    //client capable of using service ids registered with eureka to locate the services => creates proxy to rest template to do it
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

package edu.omur.msworld.moviecatalogservice.config;

import edu.omur.msworld.corelib.core.RestTemplateClientInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class Beans {

    //TODO: move to core-library
    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateClientInterceptor()));
        return restTemplate;
    }
}

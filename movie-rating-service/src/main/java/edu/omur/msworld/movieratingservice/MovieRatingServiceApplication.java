package edu.omur.msworld.movieratingservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages="edu.omur.msworld")
public class MovieRatingServiceApplication {
    private static final Logger logger = LogManager.getLogger(MovieRatingServiceApplication.class);

    @Value("${microservice-world.movie-rating-service.config-test:configError}")
    private String configTestValue;

    public static void main(String[] args) {
        SpringApplication.run(MovieRatingServiceApplication.class, args);
    }

    @PostConstruct
    private void print() {
        logger.info("configTestValue:" + configTestValue);
    }
}

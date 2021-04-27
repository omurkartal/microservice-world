package edu.omur.msworld.movieinfoservice;

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
public class MovieInfoServiceApplication {
    private static final Logger logger = LogManager.getLogger(MovieInfoServiceApplication.class);

    @Value("${microservice-world.movie-info-service.config-test:configError}")
    private String configTestValue;

    public static void main(String[] args) {
        SpringApplication.run(MovieInfoServiceApplication.class, args);
    }

    @PostConstruct
    private void print() {
        logger.info("configTestValue:" + configTestValue);
    }
}

package edu.omur.msworld.moviecatalogservice;

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
public class MovieCatalogServiceApplication {
    private static final Logger logger = LogManager.getLogger(MovieCatalogServiceApplication.class);

    @Value("${microservice-world.movie-catalog-service.config-test:configError}")
    private String configTestValue;

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

    @PostConstruct
    private void print() {
        logger.info("configTestValue:" + configTestValue);
    }
}

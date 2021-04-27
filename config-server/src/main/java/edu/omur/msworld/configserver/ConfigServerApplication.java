package edu.omur.msworld.configserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication {
    private static final Logger logger = LogManager.getLogger(ConfigServerApplication.class);

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.cloud.config.server.git.uri}")
    private String gitUri;

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @PostConstruct
    public void print() {
        logger.info("serverPort     : {}", serverPort);
        logger.info("applicationName: {}", applicationName);
        logger.info("gitUri         : {}", gitUri);
    }
}

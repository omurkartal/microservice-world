package edu.omur.msworld.corelib.boot;

import edu.omur.msworld.corelib.model.Constants;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class ApplicationContext {
    Environment environment;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    public ApplicationContext(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void buildContext() {
        ThreadContext.put(Constants.KEY_APPLICATION_NAME, applicationName);
    }

    public String getApplicationName() {
        return applicationName;
    }
}
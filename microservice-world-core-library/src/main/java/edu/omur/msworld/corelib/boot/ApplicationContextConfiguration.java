package edu.omur.msworld.corelib.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationContextConfiguration {

    @Bean
    @ConditionalOnMissingBean(ApplicationContext.class)
    public ApplicationContext applicationContext(@Autowired Environment environment) {
        return new ApplicationContext(environment);
    }
}
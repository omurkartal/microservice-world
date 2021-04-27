package edu.omur.msworld.movieratingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class ConfigurationSwagger {
    @Autowired
    Optional<BuildProperties> build;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("edu.omur.msworld.movieratingservice.controller"))
                .paths(PathSelectors.ant("/ratings/*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API - Components Service")
                .description("Managing Components.")
                .build();
    }
}
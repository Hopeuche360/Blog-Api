package com.example.week9task.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

//@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.example.week9task"))
                .build()
                .apiInfo(apiDetails());
    }
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Blog REST API", "Restful API for Blog model", "1.0", "Free to use",
                new springfox.documentation.service.Contact("Blog", "http://localhost:8080", "qfg@g.com"),
                "API License", "http://localhost:8080",
                Collections.emptyList());
    }
}

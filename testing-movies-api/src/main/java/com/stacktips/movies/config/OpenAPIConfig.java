package com.stacktips.movies.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        Info apiInfo = new Info().title("Movies API")
                .description("RESTful API documentation for movies.")
                .version("1.0");

        return new OpenAPI()
                .info(apiInfo);
    }

}
package com.stacktips.movies.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {

        Info apiInfo = new Info().title("Movies API")
                .description("RESTful API documentation for movies.")
                .version("1.0");
//                .license(new License().name("Apache 2.0").url("http://stacktips.com"));


        return new OpenAPI()
                .info(apiInfo);

//                .externalDocs(new ExternalDocumentation()
//                        .description("Movies API Wiki")
//                        .url("https://github.org/npanigrahy"));
    }

}
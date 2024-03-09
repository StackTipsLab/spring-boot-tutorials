package com.stacktips.movies.services;


import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("dev")
@Component
@ConfigurationProperties(prefix = "movies-api")
public class TestService {

    @NotNull
    String version;

    String threadPoolSize;

    List<String> data;

}

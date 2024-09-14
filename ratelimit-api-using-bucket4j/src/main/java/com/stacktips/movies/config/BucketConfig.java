package com.stacktips.movies.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "rate-limiting")
public class BucketConfig {

    private Map<String, ClientBucketConfig> clients;

    @Data
    public static class ClientBucketConfig {

        private int capacity;
        private int refillTokens;
        private Duration refillDuration;
    }
}
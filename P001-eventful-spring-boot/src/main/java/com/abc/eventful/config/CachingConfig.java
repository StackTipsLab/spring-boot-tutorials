package com.abc.eventful.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("events", "categories");
    }

    @CacheEvict(allEntries = true, value = {"events", "categories"})
    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        System.out.println("Flush cache flushed!");
    }
}


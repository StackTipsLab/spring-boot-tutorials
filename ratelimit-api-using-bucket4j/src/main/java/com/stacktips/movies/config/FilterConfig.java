package com.stacktips.movies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<RateLimitingFilter> rateLimitingFilter() {
//        FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RateLimitingFilter());
//        registrationBean.addUrlPatterns("/hello/*");
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<RateLimitingClientFilter> rateLimitingClientFilter(
            @Autowired BucketConfig bucketConfig) {
        FilterRegistrationBean<RateLimitingClientFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitingClientFilter(bucketConfig));
        registrationBean.addUrlPatterns("/hello/*");
        return registrationBean;
    }
}
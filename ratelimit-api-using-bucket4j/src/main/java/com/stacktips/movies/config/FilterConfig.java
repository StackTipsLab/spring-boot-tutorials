package com.stacktips.movies.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<RateLimitingFilter> RateLimitingFilter() {
//        FilterRegistrationBean<RateLimitingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new RateLimitingFilter());
//        registrationBean.addUrlPatterns("/api/1.0/movies/*");
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<RateLimitingClientFilter> rateLimitingClientFilter() {
        FilterRegistrationBean<RateLimitingClientFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitingClientFilter());
        registrationBean.addUrlPatterns("/api/1.0/movies/*");
        return registrationBean;
    }
}
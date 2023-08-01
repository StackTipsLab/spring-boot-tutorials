package com.stacktips.config;

import com.stacktips.interceptors.BasicAuthHandlerInterceptor;
import com.stacktips.interceptors.LogHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogHandlerInterceptor())
                .order(2);
        registry.addInterceptor(new BasicAuthHandlerInterceptor())
                .order(1)
                .addPathPatterns("/products/new");

    }
}

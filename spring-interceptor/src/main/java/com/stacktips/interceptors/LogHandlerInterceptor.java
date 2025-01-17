package com.stacktips.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class LogHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LogHandlerInterceptor::preHandle()");
        logRequestDetails(request);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("LogHandlerInterceptor::postHandle()");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("LogHandlerInterceptor::afterCompletion()");
        logResponseDetails(response);
    }


    private void logRequestDetails(HttpServletRequest request) {
        log.info("Request: {}: {}", request.getMethod(), request.getRequestURL());
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        log.info("Request headers: {}", headers);

        try {
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            log.info("Request body: {}", body);
        } catch (IOException e) {
            log.error("Error reading the request body: ", e);
        }
    }

    private void logResponseDetails(HttpServletResponse response) {
        log.info("Response: {}", HttpStatusCode.valueOf(response.getStatus()));
        Map<String, String> headers = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            headers.put(headerName, response.getHeader(headerName));
        }
        log.info("Response headers: {}", headers);
    }
}

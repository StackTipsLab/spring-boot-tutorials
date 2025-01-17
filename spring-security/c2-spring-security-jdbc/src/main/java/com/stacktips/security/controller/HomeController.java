package com.stacktips.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/private")
    public String home() {
        return "This is a secured page!";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public page!";
    }
}
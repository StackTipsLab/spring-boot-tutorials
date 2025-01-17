package com.stacktips.movies.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/hello",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class HelloController {

    @GetMapping
    public Map<String, String> hello() {
        return Collections.singletonMap("hello", "world!");
    }
}

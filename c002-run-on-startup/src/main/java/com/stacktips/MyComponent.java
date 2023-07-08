package com.stacktips;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MyComponent {

    @PostConstruct
    public void run() {
        log.info("MyComponent::run()");
    }
}

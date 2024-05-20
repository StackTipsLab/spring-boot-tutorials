package com.stacktips.app;

import com.stacktips.app.service.ImporterService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class ConfigurationPropertyValidationApplication implements CommandLineRunner {

    private final ImporterService service;

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationPropertyValidationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.importData();
    }
}

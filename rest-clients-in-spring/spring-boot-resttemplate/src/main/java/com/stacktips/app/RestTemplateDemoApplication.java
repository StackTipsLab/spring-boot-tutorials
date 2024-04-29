package com.stacktips.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class RestTemplateDemoApplication implements CommandLineRunner {

    private final ProductApiClient productApiClient;

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<Product> product = productApiClient.getProduct("1");
        log.info("Status code {}", product.getStatusCode());
        log.info("Product details: {}", product.getBody());
    }
}

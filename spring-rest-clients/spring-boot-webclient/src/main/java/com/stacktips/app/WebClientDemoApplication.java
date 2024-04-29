package com.stacktips.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class WebClientDemoApplication implements CommandLineRunner {

    private final ProductApiClient productApiClient;

    public static void main(String[] args) {
        SpringApplication.run(WebClientDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mono<ResponseEntity<Product>> productMono = productApiClient.getProduct("1");
        productMono.subscribe(
                product -> {
                    log.info("Status code {}", product);
                },
                error -> {
                    log.error("Error fetching products", error);
                });
    }
}

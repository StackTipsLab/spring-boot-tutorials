package com.stacktips.app;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@Component
public class ProductApiClient {

    @Value("${product-api.key}")
    private String apiKey;

    private final WebClient webClient;

    public ProductApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://fakestoreapi.com")
                .build();
    }

    public Mono<ResponseEntity<Product>> getProduct(String productId) {
        return webClient.get()
                .uri("/products/{productId}", productId)
                .retrieve()
                .toEntity(Product.class);
    }

    public Mono<Product> createProduct(Product product) {
        return webClient.post()
                .uri("/products")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-API-KEY", apiKey)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    public Mono<Void> updateProduct(String productId, Product product) {
        return webClient.put()
                .uri("/products/{productId}", productId)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> deleteProduct(String productId) {
        return webClient.delete()
                .uri("/products/{productId}", productId)
                .retrieve()
                .bodyToMono(Void.class);
    }
}

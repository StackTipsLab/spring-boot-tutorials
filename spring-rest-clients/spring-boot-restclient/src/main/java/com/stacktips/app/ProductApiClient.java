package com.stacktips.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProductApiClient {

    private static final String BASE_URL = "https://fakestoreapi.com";

    @Value("${product-api.key}")
    private String apiKey;

    private final RestClient restClient;

    public ProductApiClient() {
        this.restClient = RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ResponseEntity<Product> getProduct(String productId) {
        return restClient.get()
                .uri("/products/{productId}", productId)
                .retrieve()
                .toEntity(Product.class);
    }

    public ResponseEntity<Product> createProduct(Product product) {
        return restClient.post()
                .uri("/products")
                .header("X_API_KEY", apiKey)
                .body(product)
                .retrieve()
                .toEntity(Product.class);
    }

    public void updateProduct(Product product) {
        restClient.put()
                .uri("/products/{productId}", product.getId())
                .header("X_API_KEY", apiKey)
                .body(product)
                .retrieve().toEntity(Product.class);
    }

    public void deleteProduct(String productId) {
        restClient.delete()
                .uri("/products/{productId}", productId)
                .header("X_API_KEY", apiKey)
                .retrieve().toBodilessEntity();
    }
}
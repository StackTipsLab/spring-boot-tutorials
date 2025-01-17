package com.stacktips.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductApiClient {

    private static final String POST_API = "https://fakestoreapi.com/products";
    private static final String GET_API = "https://fakestoreapi.com/products/{productId}";
    private static final String DELETE_API = "https://fakestoreapi.com/products/{productId}";

    @Value("${product-api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ProductApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<Product> getProduct(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        return restTemplate.getForEntity(GET_API, Product.class, params);
    }

    public Product createProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);
        return restTemplate.postForObject(POST_API, httpEntity, Product.class);
    }

    public void updateProduct(Product product) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", product.getId());
        restTemplate.put(GET_API, product, params);
    }

    public void deleteProduct(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);
        restTemplate.delete(DELETE_API, params);
    }
}
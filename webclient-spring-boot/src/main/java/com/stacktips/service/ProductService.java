package com.stacktips.service;

import com.stacktips.domain.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final WebClient webClient;

    public ProductService() {
        this.webClient = WebClient.create("https://fakestoreapi.com");
    }

    public Mono<Product[]> getAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(Product[].class);
    }

    public Mono<Product> getProductById(int productId) {
        return webClient.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class);
    }

    public Mono<Product> addProduct(Product product) {
        return webClient.post()
                .uri("/products")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class);
    }

    public Mono<Void> deleteProduct(int productId) {
        return webClient.method(HttpMethod.DELETE)
                .uri("/products/{id}", productId)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
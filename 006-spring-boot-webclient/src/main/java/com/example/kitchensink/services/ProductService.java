package com.example.kitchensink.services;

import com.example.kitchensink.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private static final String POST_API = "https://fakestoreapi.com/products";
    private static final String GET_API = "https://fakestoreapi.com/products/{productId}";
    private static final String DELETE_API = "https://fakestoreapi.com/products/{productId}";

    private final WebClient webClient;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        webClient = WebClient.builder().baseUrl("https://fakestoreapi.com").build();
    }


    public Flux<Product> getProductAsFlux() {
        Flux<Product> result = webClient.get().uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
        return result;
    }

    public Mono<Product> getProductAsString(String productId) {
        Mono<Product> result = webClient.get().uri("/products/{productId}", productId)
                .retrieve()
                .bodyToMono(Product.class);
        return result;
    }



//WebClient.RequestHeadersSpec<?> test = webClient.get().uri("/products/{productId}", productId);
//
//        Mono<String> jsonResponse =  test.exchangeToMono(response -> {
//            if (response.statusCode() == HttpStatus.OK) {
//                return response.bodyToMono(String.class);
//            } else {
//                return Mono.just("Error response");
//            }
//        });
//        return jsonResponse.subscribe().block();
}
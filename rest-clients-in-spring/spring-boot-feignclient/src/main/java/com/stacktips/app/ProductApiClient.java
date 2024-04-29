package com.stacktips.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "product-api", url = "https://fakestoreapi.com")
public interface ProductApiClient {

    @GetMapping("/products/{productId}")
    ResponseEntity<Product> getProduct(@PathVariable("productId") String productId);

    @PostMapping("/products")
    Product createProduct(@RequestBody Product product, @RequestHeader("X-API-KEY") String apiKey);

    @PutMapping("/products/{productId}")
    void updateProduct(@PathVariable("productId") String productId, @RequestBody Product product);

    @DeleteMapping("/products/{productId}")
    void deleteProduct(@PathVariable("productId") String productId);
}

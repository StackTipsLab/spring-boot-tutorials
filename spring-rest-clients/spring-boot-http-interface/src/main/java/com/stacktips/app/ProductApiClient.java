package com.stacktips.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange(url = "/products")
public interface ProductApiClient {

    @GetExchange(url = "/{productId}")
    ResponseEntity<Product> getProduct(@PathVariable String productId);

    @PostExchange
    Product createProduct(@RequestBody Product product);

    @PutExchange(url = "/{productId}")
    void updateProduct(@PathVariable String productId, @RequestBody Product product,
                       @RequestHeader(name = "X_API_KEY") String apikey);

    @DeleteExchange(url = "/{productId}")
    void deleteProduct(@PathVariable String productId,
                       @RequestHeader(name = "X_API_KEY") String apikey);
}
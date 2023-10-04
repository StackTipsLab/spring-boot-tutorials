package com.example.kitchensink.rest;

import com.example.kitchensink.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "product-api", url = "https://fakestoreapi.com", decode404 = true)
public interface ProductApiClient {

    @GetMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> fetchProducts()
            throws IOException;
}

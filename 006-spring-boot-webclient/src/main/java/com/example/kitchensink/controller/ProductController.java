package com.example.kitchensink.controller;

import com.example.kitchensink.models.Product;
import com.example.kitchensink.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products/1.0", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductService myService;

    public ProductController(ProductService myService) {
        this.myService = myService;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> products() {
        List<Product> result = myService.getProductAsFlux().collectList().block();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/products/{productId}")
    public ResponseEntity<Product> productById(@PathVariable String productId) {
        Product product = myService.getProductAsString(productId).block();
        return ResponseEntity.ok(product);
    }

}

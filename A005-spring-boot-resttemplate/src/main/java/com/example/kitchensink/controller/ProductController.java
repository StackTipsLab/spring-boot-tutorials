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

@RestController
@RequestMapping(value = "/api/products/1.0", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductService myService;

    public ProductController(ProductService myService) {
        this.myService = myService;
    }

    //    @GetMapping(path = "/products")
//    public ResponseEntity<List<Product>> products() throws IOException {
//        return ResponseEntity.ok(myService.getProducts());
//    }

    @GetMapping(path = "/products/{productId}")
    public ResponseEntity<Product> productById(@PathVariable String productId) throws IOException {

        return ResponseEntity.ok(myService.getProduct(productId));
    }

}

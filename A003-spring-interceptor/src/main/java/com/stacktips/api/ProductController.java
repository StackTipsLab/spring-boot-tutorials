package com.stacktips.api;

import com.stacktips.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/products",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    @GetMapping(value = "/list")
    public List<Product> getProducts() {
        log.info("ProductController::getProducts()");

        return List.of(
                new Product(100L, "iPhone 12", 600.0),
                new Product(101L, "iPhone 13", 800.0),
                new Product(102L, "iPhone 14", 1000.0)
        );
    }

    @PostMapping(value = "/new")
    public Product createProduct(@RequestBody Product product) {
        log.info("ProductController::createProduct()");

        return product;
    }
}

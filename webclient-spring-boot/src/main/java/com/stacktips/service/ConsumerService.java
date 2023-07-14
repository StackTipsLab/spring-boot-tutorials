package com.stacktips.service;

import com.stacktips.domain.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ConsumerService {

    private final ProductService productService;

    public ConsumerService(ProductService productService) {
        this.productService = productService;
    }

    public void consumeProductsApi() {
        Mono<Product[]> productsMono = productService.getAllProducts();
        productsMono.subscribe(
                products -> {
                    //TODO write your logic to handle list of products repose
                },
                error -> {
                    // Handle error cases
                    error.printStackTrace();
                }
        );



        Mono<Product> productMono = productService.getProductById(1);
        productMono.subscribe(
                product -> {
                    //TODO write your logic to handle product response
                },
                error -> {
                    // Handle error cases
                    error.printStackTrace();
                }
        );



        Product product = new Product();
        product.setTitle("test product");
        product.setPrice(13.5);
        product.setDescription("lorem ipsum set");
        product.setImage("https://i.pravatar.cc");
        product.setCategory("electronic");
        Mono<Product> addedProductMono = productService.addProduct(product);
        addedProductMono.subscribe(
                addedProduct -> {
                    //TODO Added product
                },
                error -> {
                    // Handle error cases
                    error.printStackTrace();
                }
        );




    }
}
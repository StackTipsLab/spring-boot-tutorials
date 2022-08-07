package com.stacktips.api;

import com.stacktips.dto.ProductDto;
import com.stacktips.model.Product;
import com.stacktips.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/1.0/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto)
            throws URISyntaxException {
        Product product = productService.createProduct(productDto);
        return ResponseEntity.created(new URI("/api/1.0/products/" + product.getId()))
                .body(product);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "productId") Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> createProduct(@PathVariable(value = "productId") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "productId") Long productId,
            @RequestBody ProductDto productDto) {

        Product product = productService.updateProduct(productId, productDto);
        return ResponseEntity.ok(product);
    }

}

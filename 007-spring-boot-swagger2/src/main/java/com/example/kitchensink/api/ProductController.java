package com.example.kitchensink.api;

import com.example.kitchensink.dto.ProductDto;
import com.example.kitchensink.model.Product;
import com.example.kitchensink.model.ResponseType;
import com.example.kitchensink.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products/1.0", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ProductController {

    private final ProductService stockService;

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "50", required = false) int size,
            @RequestParam(required = false) String category) throws IOException {

        return ResponseEntity.ok(stockService.searchProducts(page, size, category));
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> products() throws IOException {
        return ResponseEntity.ok(stockService.getProducts());
    }

    @GetMapping(path = "/products/{productId}")
    public ResponseEntity<Product> productById(@PathVariable String productId) throws IOException {

        return ResponseEntity.ok(stockService.findProductById(productId));
    }

    @PostMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductDto productDto)
            throws URISyntaxException {

        final Product product = stockService.creteProduct(productDto);
        return ResponseEntity
                .created(new URI("/api/products/1.0/products/" + product.getId()))
                .body(product);
    }

    @DeleteMapping(path = "/products")
    public ResponseEntity<ResponseType> deleteProduct(@RequestParam long productId) {

        boolean result = stockService.deleteProduct(productId);
        return ResponseEntity.ok(result ? ResponseType.SUCCESS : ResponseType.FAILURE);
    }
}

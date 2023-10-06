package com.example.kitchensink.service;

import com.example.kitchensink.dto.ProductDto;
import com.example.kitchensink.model.Product;
import com.example.kitchensink.rest.ProductApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductApiClient apiClient;


    public List<Product> getProducts() throws IOException {
        return apiClient.fetchProducts();
    }

    public Product creteProduct(ProductDto productDto) {
        Product product = Product.builder()
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .build();

        //TODO save product into DB
        return null;
    }

    public boolean deleteProduct(long productId) {
        //TODO delete products from DB
        return true;
    }

    public List<Product> searchProducts(int page, int size, String category) throws IOException {
        return apiClient.fetchProducts();

    }

    public Product findProductById(String productId) throws IOException {
        return apiClient.fetchProducts().get(0);
    }
}

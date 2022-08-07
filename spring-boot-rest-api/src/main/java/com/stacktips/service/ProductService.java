package com.stacktips.service;

import com.stacktips.dto.ProductDto;
import com.stacktips.exception.ProductNotFoundException;
import com.stacktips.model.Product;
import com.stacktips.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> product = repository.findById(productId);
        return product.orElseThrow(() -> new ProductNotFoundException("Product with id '" + productId + "' not found"));
    }


    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setActive(productDto.isActive());
        product.setInStock(productDto.isInStock());
        product.setUnitsAvailable(productDto.getUnitsAvailable());

        return repository.save(product);
    }

    public void deleteProduct(Long productId) {
        repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id '" + productId + "' not found"));

        repository.deleteById(productId);
    }

    public Product updateProduct(Long productId, ProductDto productDto) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id '" + productId + "' not found"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setActive(productDto.isActive());
        product.setInStock(productDto.isInStock());
        product.setUnitsAvailable(productDto.getUnitsAvailable());

        return repository.save(product);

    }

}

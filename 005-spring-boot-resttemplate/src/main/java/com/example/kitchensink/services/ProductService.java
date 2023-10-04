package com.example.kitchensink.services;

import com.example.kitchensink.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private static final String POST_API = "https://fakestoreapi.com/products";
    private static final String GET_API = "https://fakestoreapi.com/products/{productId}";
    private static final String DELETE_API = "https://fakestoreapi.com/products/{productId}";
    private final RestTemplate restTemplate;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getProductAsString(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        String result = restTemplate.getForObject(GET_API,
                String.class, params);
        return result;
    }

    public Product getProductObject(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        Product result = restTemplate.getForObject(GET_API,
                Product.class, params);

        return result;
    }


    public Product getProductAsEntity(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);
        ResponseEntity<Product> result = restTemplate.getForEntity(GET_API, Product.class, params);

        HttpStatus statusCode = result.getStatusCode();
        Product product = result.getBody();

        return product;
    }


    public Product getProductWithHeader(String productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", "YOUR_API_KEY");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        ResponseEntity<Product> result = restTemplate.exchange(GET_API, HttpMethod.GET, httpEntity, Product.class, params);
        return result.getBody();
    }


    public void postProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", "YOUR_API_KEY");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> httpEntity = new HttpEntity<>(product, headers);

        Product createdProduct = restTemplate.postForObject(POST_API, httpEntity, Product.class);
    }

    public void putProduct(Product product) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", product.getId());

        restTemplate.put(GET_API, product, params);
    }

    public void deleteProduct(String productId) {
        Map<String, String> params = new HashMap<>();
        params.put("productId", productId);

        restTemplate.delete(GET_API, params);
    }

}
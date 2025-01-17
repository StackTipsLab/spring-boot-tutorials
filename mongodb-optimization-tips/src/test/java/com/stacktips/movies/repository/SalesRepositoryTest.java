package com.stacktips.movies.repository;

import com.stacktips.movies.dto.TotalSales;
import com.stacktips.movies.models.Price;
import com.stacktips.movies.models.Product;
import com.stacktips.movies.models.Sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Testcontainers
@DataMongoTest(includeFilters = @ComponentScan.Filter(Repository.class))
class SalesRepositoryTest {

    @Autowired
    SalesRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Container
    static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongoDbContainer::getHost);
        registry.add("spring.data.mongodb.port", mongoDbContainer::getFirstMappedPort);
        registry.add("spring.data.mongodb.username", () -> "test_user");
        registry.add("spring.data.mongodb.password", () -> "test_password");
        registry.add("spring.data.mongodb.database", () -> "movies_db");
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
    }

    static {
        mongoDbContainer.start();
    }

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection("Product");
        mongoTemplate.dropCollection("Price");
        mongoTemplate.dropCollection("Sales");
        List<Product> products = List.of(
                new Product("productId1", "Product A", "Brand A"),
                new Product("productId2", "Product B", "Brand B"),
                new Product("productId3", "Product B", "Brand C")
        );
        List<Price> prices = List.of(
                new Price("productId1", 100),
                new Price("productId2", 200),
                new Price("productId3", 150)
        );
        List<Sales> sales = List.of(
                new Sales("saleId1", "productId1", 3, getSaleDate(1)),
                new Sales("saleId2", "productId1", 3, getSaleDate(3)),
                new Sales("saleId3", "productId1", 5, getSaleDate(1)),
                new Sales("saleId4", "productId2", 3, getSaleDate(1))
        );

        mongoTemplate.insertAll(products);
        mongoTemplate.insertAll(prices);
        mongoTemplate.insertAll(sales);
    }

    @Test
    void testCalculateLastMonthSales() {
        List<TotalSales> totalSalesList = repository.calculateLastMonthSales();
        System.out.println(totalSalesList);

        assertThat(totalSalesList, is(notNullValue()));
        assertThat(totalSalesList.size(), comparesEqualTo(2));

        TotalSales productASales = totalSalesList.stream()
                .filter(totalSales -> "Product A".equals(totalSales.getProductName()))
                .findFirst()
                .orElse(null);

        TotalSales productBSales = totalSalesList.stream()
                .filter(totalSales -> "Product B".equals(totalSales.getProductName()))
                .findFirst()
                .orElse(null);

        assertThat(productASales, is(notNullValue()));
        assertThat(productBSales, is(notNullValue()));
        assertThat(productASales.getTotalSales(), is(800.0));

        assertThat(productBSales, is(notNullValue()));
        assertThat(productBSales.getTotalSales(), is(600.0));
    }

    Date getSaleDate(int monthsToGoBack) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -monthsToGoBack);
        return calendar.getTime();
    }
}
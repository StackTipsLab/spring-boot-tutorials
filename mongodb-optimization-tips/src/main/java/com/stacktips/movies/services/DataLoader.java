package com.stacktips.movies.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.stacktips.movies.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final String FILE_PATH = "src/main/resources/products.csv";

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = parseCsv(FILE_PATH, Product.class)
                .stream()
                .map(row -> new Product(row.getId(), row.getName(), row.getBrand()))
                .toList();

        mongoTemplate.bulkOps(BulkOperations.BulkMode.ORDERED, Product.class)
                .insert(products)
                .execute();
    }

    public static <T> List<T> parseCsv(String filePath, Class<T> type) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            return new CsvToBeanBuilder<T>(reader).withType(type).build().parse();
        }
    }
}

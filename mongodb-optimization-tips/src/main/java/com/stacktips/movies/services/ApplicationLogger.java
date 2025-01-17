package com.stacktips.movies.services;

import com.stacktips.movies.models.LogEntry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationLogger implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    private static final long MAX_SIZE = 5 * 1024 * 1024;
    private static final long MAX_DOCUMENTS = 100;

    @PostConstruct
    public void init() {
        if (!mongoTemplate.collectionExists(LogEntry.class)) {
            mongoTemplate.createCollection(LogEntry.class,
                    CollectionOptions.empty().capped()
                            .size(MAX_SIZE)
                            .maxDocuments(MAX_DOCUMENTS));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 1500; i++) {
            mongoTemplate.save(new LogEntry("Log entry " + i));
        }
        log.info("Size: {0}", mongoTemplate.count(new Query(), LogEntry.class));
    }
}
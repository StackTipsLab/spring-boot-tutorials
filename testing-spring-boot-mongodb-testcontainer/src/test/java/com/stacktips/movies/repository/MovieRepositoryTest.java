package com.stacktips.movies.repository;

import com.stacktips.movies.dto.SearchRequest;
import com.stacktips.movies.models.ContentRating;
import com.stacktips.movies.models.Movie;
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
import org.testcontainers.utility.MountableFile;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Testcontainers
@DataMongoTest(includeFilters = @ComponentScan.Filter(Repository.class))
class MovieRepositoryTest {

    @Autowired
    MovieRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Container
    static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017)
            .withCopyFileToContainer(MountableFile.forClasspathResource("./init-schema.js"),
                    "/docker-entrypoint-initdb.d/init-script.js");

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

    @Test
    void testMoviesCount() {
        List<Movie> movies = mongoTemplate.findAll(Movie.class);
        assertThat(4, is(movies.size()));
    }

    @Test
    void testSearchMovies() {
        SearchRequest searchRequest = new SearchRequest(ContentRating.G, "EN", "Action");
        List<Movie> movies = repository.searchMovies(searchRequest);
        assertThat(2, is(movies.size()));
        assertThat(movies.get(0).title(), is("Transformers: Rise of the Beasts"));
        assertThat(movies.get(0).language(), is("EN"));
        assertThat(movies.get(0).region(), is("USA"));
        assertThat(movies.get(0).rating(), is(ContentRating.valueOf("G")));
    }
}
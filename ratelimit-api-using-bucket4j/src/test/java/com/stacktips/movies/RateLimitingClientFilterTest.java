package com.stacktips.movies;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RateLimitingClientFilterTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testRateLimiting() {
        for (int i = 0; i < 10; i++) {
            given().header("X-API-Key", "app1")
                    .get("/api/1.0/movies").then()
                    .statusCode(200)
                    .header("X-Rate-Limit-Remaining", notNullValue());
        }

        for (int i = 0; i < 10; i++) {
            given().header("X-API-Key", "app2")
                    .get("/api/1.0/movies").then()
                    .statusCode(200)
                    .header("X-Rate-Limit-Remaining", notNullValue());
        }

        given().header("X-API-Key", "app1")
                .get("/api/1.0/movies")
                .then()
                .statusCode(429)
                .header("X-Rate-Limit-Retry-After-Seconds", notNullValue());

        given().header("X-API-Key", "app2")
                .get("/api/1.0/movies")
                .then()
                .statusCode(429)
                .header("X-Rate-Limit-Retry-After-Seconds", notNullValue());
    }
}

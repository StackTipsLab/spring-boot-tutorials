package com.stacktips.movies;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RateLimitingFilterTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testRateLimiting() {
        for (int i = 0; i < 10; i++) {
            Response response = get("/api/1.0/movies");
            response.then()
                    .statusCode(200)
                    .header("X-Rate-Limit-Remaining", notNullValue());
            printResponse(response);
        }

        get("/api/1.0/movies")
                .then()
                .statusCode(429)
                .header("X-Rate-Limit-Retry-After-Seconds", notNullValue());
    }

    private void printResponse(Response response) {
        response.headers().forEach(System.out::println);
        System.out.println(response.getBody().asString());
        System.out.println("-----------------------------");
    }
}

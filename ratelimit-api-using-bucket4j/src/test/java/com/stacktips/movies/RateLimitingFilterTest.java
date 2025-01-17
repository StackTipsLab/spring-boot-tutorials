package com.stacktips.movies;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RateLimitingFilterTest {

    @Test
    void testRateLimiting() {
        for (int i = 0; i < 10; i++) {
            get("/hello")
                    .then()
                    .statusCode(200)
                    .header("X-Rate-Limit-Remaining", notNullValue());
        }

        get("/hello")
                .then()
                .statusCode(429)
                .header("X-Rate-Limit-Retry-After-Seconds", notNullValue());
    }
}

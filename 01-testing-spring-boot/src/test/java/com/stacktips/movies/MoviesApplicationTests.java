package com.stacktips.movies;

import com.stacktips.movies.api.GreetingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class MoviesApplicationTests {

    @Autowired
    private GreetingController controller;

    @Test
    void contextLoads() {
        assertThat(controller, is(notNullValue()));
    }

}

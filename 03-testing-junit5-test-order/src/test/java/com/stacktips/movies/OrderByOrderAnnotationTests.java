package com.stacktips.movies;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderByOrderAnnotationTests {

    @Test
    @Order(1)
    void test1() {
        assertTrue(true);
    }

    @Test
    @Order(3)
    void test2() {
        assertTrue(true);
    }

    @Test
    @Order(2)
    void test3() {
        assertTrue(true);
    }
}


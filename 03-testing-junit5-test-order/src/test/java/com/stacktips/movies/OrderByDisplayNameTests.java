package com.stacktips.movies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class OrderByDisplayNameTests {

    @Test
    @DisplayName("testMethod1")
    void test1() {
        assertTrue(true);
    }

    @Test
    @DisplayName("testMethod2")
    void test2() {
        assertTrue(true);
    }

    @Test
    @DisplayName("testMethod3")
    void test3() {
        assertTrue(true);
    }
}
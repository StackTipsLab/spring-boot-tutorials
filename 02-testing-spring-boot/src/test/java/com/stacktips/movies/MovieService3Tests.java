package com.stacktips.movies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

//class ParameterCountOrder implements MethodOrderer {
//    @Override
//    public void orderMethods(MethodOrdererContext context) {
//        Comparator<MethodDescriptor> comparator =
//                Comparator.comparingInt(md1 -> md1.getMethod().getParameterCount());
//        context.getMethodDescriptors()
//                .sort(comparator.reversed());
//    }
//}

@TestMethodOrder(ParameterCountOrder.class)
class MovieService3Tests {

    @Test
    @DisplayName("testMethod1")
    void test1() {
        assertTrue(true);
    }

    @ParameterizedTest(name = "{index} ==> product={0}")
    @CsvSource({
            "iPhone 15",
            "Mx Master 3S"
    })
    @DisplayName("testMethod2")
    void test2(String product) {
        assertTrue(true);
    }

    @DisplayName("testMethod3")
    @ParameterizedTest(name = "{index} ==> product={0}, price={1}")
    @CsvSource({
            "iPhone 15, 999",
            "Mx Master 3S, 89"
    })
    void test3(String product, double price) {
        assertTrue(true);
    }

}




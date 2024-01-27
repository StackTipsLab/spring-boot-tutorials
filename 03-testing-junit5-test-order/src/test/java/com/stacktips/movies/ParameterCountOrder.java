package com.stacktips.movies;

import org.junit.jupiter.api.MethodDescriptor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

import java.util.Comparator;

class ParameterCountOrder implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
        Comparator<MethodDescriptor> comparator =
                Comparator.comparingInt(md1 -> md1.getMethod().getParameterCount());
        context.getMethodDescriptors()
                .sort(comparator.reversed());
    }
}
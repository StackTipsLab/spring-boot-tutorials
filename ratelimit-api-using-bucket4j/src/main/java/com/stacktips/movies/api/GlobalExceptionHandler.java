package com.stacktips.movies.api;

import com.stacktips.movies.exception.MovieNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    ResponseEntity<Void> handleNotFoundException(MovieNotFoundException ex) {
        return ResponseEntity.notFound()
                .build();
    }
}

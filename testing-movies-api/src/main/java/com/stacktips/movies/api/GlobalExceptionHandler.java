package com.stacktips.movies.api;

import com.stacktips.movies.exception.MovieNotFoundException;
import com.stacktips.movies.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    ResponseEntity<List<ApiError>> handleNotFoundException(MovieNotFoundException ex) {
        final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.name(), ex.getMessage());

        return new ResponseEntity<>(List.of(apiError), HttpStatus.NOT_FOUND);
    }

}

package com.stacktips.api;

import com.stacktips.exception.ProductNotFoundException;
import com.stacktips.model.Error;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handle404Error(ProductNotFoundException e) {
        return ResponseEntity.badRequest().body(new Error(e.getMessage(), HttpStatus.NOT_FOUND.toString()));
    }

}

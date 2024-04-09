package com.stacktips.app.controller;

import com.stacktips.app.dto.ApiError;
import com.stacktips.app.exception.UserAlreadyExistsException;
import com.stacktips.app.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Void> handleNotFoundException(UserNotFoundException ex) {
        log.error("Error", ex);
        return ResponseEntity.notFound()
                .build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<ApiError> handleNotFoundException(UserAlreadyExistsException ex) {
        log.error("Error", ex);
        ApiError apiError = ApiError.builder()
                .message("User exist!")
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

}
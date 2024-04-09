package com.stacktips.app.controller;

import com.stacktips.app.dto.ApiError;
import com.stacktips.app.exception.AuthException;
import com.stacktips.app.exception.DuplicateUserException;
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

    @ExceptionHandler(AuthException.class)
    ResponseEntity<ApiError> handleAuthException(AuthException ex) {
        ApiError apiError = ApiError.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateUserException.class)
    ResponseEntity<ApiError> handleUserAlreadyExistsException(DuplicateUserException ex) {
        ApiError apiError = ApiError.builder()
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
package com.stacktips.movies.api;

import com.stacktips.movies.dto.ErrorResponse;
import com.stacktips.movies.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error");
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(MovieNotFoundException.class)
//     ResponseEntity<Void> handleNotFoundException(MovieNotFoundException ex) {
//        return ResponseEntity.notFound()
//                .build();
//    }

}

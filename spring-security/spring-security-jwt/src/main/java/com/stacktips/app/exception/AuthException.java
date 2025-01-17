package com.stacktips.app.exception;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
package com.example.employeemanager.exception;

public class JWTAuthenticationException extends RuntimeException {
    public JWTAuthenticationException(String message) {
        super(message);
    }
}

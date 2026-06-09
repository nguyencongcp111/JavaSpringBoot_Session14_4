package com.example.employeemanager.exception;

public class WrongUsernameOrPasswordException extends RuntimeException {
    public WrongUsernameOrPasswordException(String message) {
        super(message);
    }
}

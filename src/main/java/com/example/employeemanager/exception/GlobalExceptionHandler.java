package com.example.employeemanager.exception;

import com.example.employeemanager.models.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WrongUsernameOrPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWrongUsernameOrPassword(
            WrongUsernameOrPasswordException exception,
            WebRequest request
    ) {
        System.out.println("EXCEPTION HANDLER CALLED");

        ErrorResponse response = new ErrorResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.UNAUTHORIZED);
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

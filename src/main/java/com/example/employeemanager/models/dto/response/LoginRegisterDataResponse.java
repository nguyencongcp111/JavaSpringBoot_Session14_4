package com.example.employeemanager.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRegisterDataResponse<T> {
    private HttpStatus status;
    private String username;
    private String message;
}

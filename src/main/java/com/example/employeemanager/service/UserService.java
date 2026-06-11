package com.example.employeemanager.service;

import com.example.employeemanager.models.dto.request.LoginRequest;
import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.dto.response.JWTResponse;
import com.example.employeemanager.models.entity.User;
import jakarta.servlet.http.HttpSession;


public interface UserService {
    User registerUser(RegisterRequest registerRequest);

    JWTResponse loginUser(LoginRequest loginRequest, HttpSession httpSession);
}

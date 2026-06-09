package com.example.employeemanager.service;

import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.entity.User;

public interface UserService {
    User registerUser(RegisterRequest registerRequest);
}

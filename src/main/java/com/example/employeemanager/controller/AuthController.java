package com.example.employeemanager.controller;

import com.example.employeemanager.models.dto.request.LoginRequest;
import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.dto.response.DataResponse;
import com.example.employeemanager.models.dto.response.LoginRegisterDataResponse;
import com.example.employeemanager.models.entity.User;
import com.example.employeemanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<DataResponse<User>> login(
            @RequestBody LoginRequest loginRequest,
            HttpSession httpSession
    ) {

        return ResponseEntity.ok(
                new DataResponse<>(
                        HttpStatus.OK,
                        userService.loginUser(loginRequest, httpSession),
                        "Đăng nhập thành công"
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse<User>> register (
            @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(
                new DataResponse<>(
                        HttpStatus.CREATED,
                        userService.registerUser(registerRequest),
                        "Đã tạo người dùng"
                ), HttpStatus.CREATED
        );
    }
}

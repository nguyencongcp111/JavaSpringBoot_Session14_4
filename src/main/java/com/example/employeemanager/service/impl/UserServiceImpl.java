package com.example.employeemanager.service.impl;

import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.entity.Roles;
import com.example.employeemanager.models.entity.User;
import com.example.employeemanager.repository.UserRepository;
import com.example.employeemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest registerRequest) {
        boolean find = userRepository.existsByUsername(registerRequest.getUsername());
        if (find) {
            throw new RuntimeException("Username da ton tai");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .role(Roles.USER)
                .enabled(true)
                .build();

        return userRepository.save(user);
    }
}

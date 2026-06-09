package com.example.employeemanager.service.impl;

import com.example.employeemanager.exception.WrongUsernameOrPasswordException;
import com.example.employeemanager.models.dto.request.LoginRequest;
import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.entity.Roles;
import com.example.employeemanager.models.entity.User;
import com.example.employeemanager.repository.UserRepository;
import com.example.employeemanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
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

    @Override
    public User loginUser(LoginRequest loginRequest, HttpSession httpSession) {
        System.out.println("LOGIN SERVICE CALLED");
        User user = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() ->
                        new WrongUsernameOrPasswordException("Sai tài khoản hoặc mật khẩu"));

        boolean matched = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!matched) {
            throw new WrongUsernameOrPasswordException(
                    "Sai tài khoản hoặc mật khẩu"
            );
        }

        httpSession.setAttribute("user", user);

        return user;
    }
}

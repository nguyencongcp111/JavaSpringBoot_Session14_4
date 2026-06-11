package com.example.employeemanager.service.impl;

import com.example.employeemanager.exception.WrongUsernameOrPasswordException;
import com.example.employeemanager.models.dto.request.LoginRequest;
import com.example.employeemanager.models.dto.request.RegisterRequest;
import com.example.employeemanager.models.dto.response.JWTResponse;
import com.example.employeemanager.models.entity.Roles;
import com.example.employeemanager.models.entity.User;
import com.example.employeemanager.repository.UserRepository;
import com.example.employeemanager.security.jwt.JWTProvider;
import com.example.employeemanager.security.principal.CustomUserDetailService;
import com.example.employeemanager.security.principal.UserPrincipal;
import com.example.employeemanager.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

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
    public JWTResponse loginUser(LoginRequest loginRequest, HttpSession httpSession) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            String role = userPrincipal.getAuthorities().stream().findFirst().get().getAuthority();

            String token = jwtProvider.generateToken(userPrincipal.getUsername(),role);

            return JWTResponse.builder()
                    .username(userPrincipal.getUsername())
                    .type("Bearer")
                    .accessToken(token)
                    .build();
        } catch (AuthenticationException e) {
            throw new WrongUsernameOrPasswordException(e.getMessage());
        }
    }
}

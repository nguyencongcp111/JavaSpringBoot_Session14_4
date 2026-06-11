package com.example.employeemanager.security.jwt;

import com.example.employeemanager.security.principal.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTProvider {
    @Value("${jwt-secret}")
    private String jwtSecret;
    @Value("${jwt-expired}")
    private Long jwtExpired;

    public String generateToken (String username, String role) {
        try {
            Date today = new Date();

            return Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setIssuedAt(today)
                    .setExpiration(new Date(today.getTime()+jwtExpired))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Không tạo được chuỗi token ", e);
        }
    }
}

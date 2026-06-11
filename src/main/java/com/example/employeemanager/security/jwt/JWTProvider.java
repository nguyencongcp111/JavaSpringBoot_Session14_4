package com.example.employeemanager.security.jwt;

import com.example.employeemanager.exception.JWTAuthenticationException;
import io.jsonwebtoken.*;
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

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new JWTAuthenticationException("Token hết hạn");
        } catch (SignatureException e) {
            throw new JWTAuthenticationException("Chữ ký token không hợp lệ");
        }catch (MalformedJwtException e) {
            throw new JWTAuthenticationException("Token không đúng định dạng");
        } catch (IllegalArgumentException e){
            throw new JWTAuthenticationException("Token rỗng");
        } catch (Exception e) {
            throw new JWTAuthenticationException("Không hỗ trợ Token");
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}

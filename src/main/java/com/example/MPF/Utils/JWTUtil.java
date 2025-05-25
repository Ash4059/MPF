package com.example.MPF.Utils;

import java.security.Key;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    
    private static final String SECRET_KEY = UUID.randomUUID().toString();
    private static final Key Key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username, long expiryMinute) {
        return Jwts.builder()
                .subject(username)
                .expiration(new java.util.Date(System.currentTimeMillis() + expiryMinute * 60 * 1000))
                .signWith(Key)
                .compact();
    }

}

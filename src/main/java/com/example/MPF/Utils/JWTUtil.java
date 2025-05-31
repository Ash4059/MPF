package com.example.MPF.Utils;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JWTUtil {

    private final SecretKey key; // Make it final
    private final long defaultExpiryMilliseconds = 60 * 60 * 1000; // Example: 1 hour default if not passed

    // Inject the secret from properties
    public JWTUtil(@Value("${jwt.secret-key}") String secretKeyString) {
        this.key = Keys.hmacShaKeyFor(secretKeyString.getBytes());
    }

    public String generateToken(String username, long expiryMinute) {
        long expiryMillis = expiryMinute > 0 ? expiryMinute * 60 * 1000 : defaultExpiryMilliseconds;
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiryMillis))
                .signWith(key, Jwts.SIG.HS256) // or simply .signWith(key) if the key itself embeds the alg
                .compact();
    }

    public String validateAndExtractUserName(String token){
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
        catch (Exception e){
            System.out.println("Error :" + e.getMessage());
        }
        return null;
    }

}

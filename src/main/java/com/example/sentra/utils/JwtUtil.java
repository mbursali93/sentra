package com.example.sentra.utils;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtUtil {

    @Value("${spring.security.auth.jwt_secret}")
    private String jwtSecret;

    @Value("${spring.environment}")
    private String environment;

    private Key key;

    // This method will be called after jwtSecret has been injected
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String extractUserId(String token) {
        if (token.startsWith("Bearer"))
            token = this.getTokenFromHeader(token);
        return extractClaim(token, Claims::getSubject);
    }

    public boolean extractIsAdmin(String token) {
        return extractClaim(token, claims -> claims.get("isAdmin", Boolean.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userId, boolean isAdmin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("isAdmin", isAdmin);
        return createToken(claims, userId);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        int data = environment.equals("product") ? 1000 * 60 * 20  : 1000 * 60 * 60 * 24 * 1000; // 20 minutes or 1000 days
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + data))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, String userId) {
        String id = extractUserId(token);
        return (userId.equals(id) && !isTokenExpired(token));
    }

    private String getTokenFromHeader(String headerToken) {

        return headerToken.substring(7);
    }

}

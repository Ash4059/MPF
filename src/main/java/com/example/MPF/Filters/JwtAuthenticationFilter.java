package com.example.MPF.Filters;

import com.example.MPF.Utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.MPF.DTO.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    public final static String GENERATE_TOKEN_PATH = "/generate-token";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(!request.getServletPath().equals(GENERATE_TOKEN_PATH)){
            filterChain.doFilter(request, response);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticationResult = this.authenticationManager.authenticate(authenticationToken);
        if(authenticationResult.isAuthenticated()){
            String token = this.jwtUtil.generateToken(authenticationResult.getName(), 15);
            response.addHeader("Authorization", "Bearer " + token);
        }
    }
}

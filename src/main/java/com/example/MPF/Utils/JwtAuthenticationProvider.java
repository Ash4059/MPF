package com.example.MPF.Utils;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationProvider(UserDetailsService userDetailsService, JWTUtil jwtUtil){
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JwtAuthenticationToken)authentication).getToken();
        String username = jwtUtil.validateAndExtractUserName(token);
        if(Objects.isNull(username)){
            throw new BadCredentialsException("Invalid JWT token");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

package com.batiaev.provisioner.controller;

import com.batiaev.provisioner.model.user.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
    private final String tokenHeader;

    public JwtAuthFilter(@Value("${authHeader}") String tokenHeader) {
        super("/**");
        this.tokenHeader = tokenHeader;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(this.tokenHeader);
        if (header == null || !header.startsWith("Bearer ")) {
            throw new IllegalStateException("No JWT token found in request headers");
        }
        String authToken = header.substring(7);
        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);
        return getAuthenticationManager().authenticate(authRequest);
    }
}

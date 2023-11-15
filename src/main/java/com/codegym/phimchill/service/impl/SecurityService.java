package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.security.JwtTokenProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

public class SecurityService implements com.codegym.phimchill.service.SecurityService{
    private JwtTokenProvider jwtTokenProvider;

    public SecurityService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public SecurityService() {
    }

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    public boolean isValidToken(String authToken) {
        String jwt = jwtTokenProvider.getJwtFromBearerToken(authToken);
        if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(authToken)) {
            return true;
        }
        return false;
    }
}

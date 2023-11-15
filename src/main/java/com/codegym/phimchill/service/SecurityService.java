package com.codegym.phimchill.service;

public interface SecurityService {
    boolean isAuthenticated();
    boolean isValidToken(String token);
}

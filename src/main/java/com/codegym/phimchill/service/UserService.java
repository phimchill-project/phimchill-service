package com.codegym.phimchill.service;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest) throws Exception;
    boolean isEmailExist(EmailRequest emailRequest);
}

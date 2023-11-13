package com.codegym.phimchill.service;

import com.codegym.phimchill.payload.request.CheckEmailExist;
import com.codegym.phimchill.payload.request.LoginRequest;
import com.codegym.phimchill.payload.request.RegisterRequest;
import com.codegym.phimchill.payload.response.LoginResponse;
import com.codegym.phimchill.payload.response.RegisterResponse;

public interface IUserService {
    LoginResponse login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest) throws Exception;
    boolean isEmailExist(CheckEmailExist checkEmailExist);
}

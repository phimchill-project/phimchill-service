package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;

public interface IUserService {
    LoginResponse login(LoginRequest loginRequest);
}

package com.codegym.phimchill.service;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;

public interface UserService {
//    String login(LoginRequest loginRequest);
    UserDto login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest) throws Exception;
    boolean isEmailExist(EmailRequest emailRequest);
    User findUserByEmail(String email) throws Exception;
    boolean updateEmail(String email, String newEmail );

    boolean updatePass(String email, String pass);
}

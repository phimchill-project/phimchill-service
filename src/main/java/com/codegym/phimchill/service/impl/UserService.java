package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements com.codegym.phimchill.service.UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setData(userConverter.converterToDTO(user));
            loginResponse.setMessage("Đăng nhập thành công!");
            return loginResponse;
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setData(null);
            loginResponse.setMessage("Tên đăng nhập hoặc mật khẩu không chính xác");
            return loginResponse;
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {
            User user = userRepository.findUserByEmail(registerRequest.getEmail());
            if (user == null ){
                User user1 = User
                        .builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .name(registerRequest.getName())
                        .build();
                userRepository.save(user1);
                RegisterDto registerDto = userConverter.converterRegister(user1);
                RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setData(registerDto);
                registerResponse.setStatusCode(200);
                registerResponse.setMessage("Register Success");
                return registerResponse;
            } else {
                throw new Exception();
        }
    }

    @Override
    public boolean isEmailExist(EmailRequest email) {
        User user = userRepository.findUserByEmail(email.getEmail());
        if(user != null) {
            return true;
        }else {
            return false;
        }
    }
}


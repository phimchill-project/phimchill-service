package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.convect.UserDTOConvect;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.IUserRepository;
import com.codegym.phimchill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private UserDTOConvect userDTOConvect;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = iUserRepository.findUserByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserDTO(userDTOConvect.convertToDTO(user));
            loginResponse.setMessage("Đăng nhập thành công!");
            return loginResponse;

        } else {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserDTO(null);
        loginResponse.setMessage("Tên đăng nhập hoặc mật khẩu không chính xác");
        return loginResponse;
        }


    }
}

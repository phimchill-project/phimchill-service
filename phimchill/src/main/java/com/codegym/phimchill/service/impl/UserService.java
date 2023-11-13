package com.codegym.phimchill.service.impl;
import com.codegym.phimchill.converter.UserDTOConverter;
import com.codegym.phimchill.convert.impl.UserConvert;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
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

    private UserDTOConverter userDTOConverter;

    private UserConvert userDTOConvect;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = iUserRepository.findUserByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserDTO(userDTOConverter.converterToDTO(user));
            loginResponse.setMessage("Đăng nhập thành công!");
            return loginResponse;
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserDTO(null);
            loginResponse.setMessage("Tên đăng nhập hoặc mật khẩu không chính xác");
            return loginResponse;
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {
            User user = iUserRepository.findUserByEmail(registerRequest.getEmail());
            if (user == null ){
                User user1 = User
                        .builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .name(registerRequest.getName())
                        .build();
                iUserRepository.save(user1);
                return userDTOConvect.convertRegister(user1);
            } else {
                throw new Exception();
        }

    }
}


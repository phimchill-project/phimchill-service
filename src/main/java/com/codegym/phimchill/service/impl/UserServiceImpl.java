package com.codegym.phimchill.service.impl;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.entity.Role;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.repository.RoleRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String  login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        return user.getName();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {
            User user = userRepository.findUserByEmail(registerRequest.getEmail());
            if (user == null ){
                String hashPassword = passwordEncoder.encode(registerRequest.getPassword());
                registerRequest.setPassword(hashPassword);
                Role role = roleRepository.findById(2L).orElse(null);
                User user1 = User
                        .builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .name(registerRequest.getName())
                        .role(role)
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

    @Override
<<<<<<< HEAD
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new Exception("Email cannot find");
        }
        return user;
=======
    public boolean updateEmail( String newEmail) {
        User existingUser = userRepository.findUserByEmail(newEmail);
        if (existingUser != null) {
            existingUser.setEmail(newEmail);
            userRepository.save(existingUser);
            return true;
        }
        return false;
>>>>>>> a8944ae78980ff5f7ebebaee44d61d32d53e282d
    }
}


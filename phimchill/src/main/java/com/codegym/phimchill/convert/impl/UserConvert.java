package com.codegym.phimchill.convert.impl;

import com.codegym.phimchill.convert.IUserConvert;
import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConvert implements IUserConvert {
    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
    public RegisterResponse convertRegister(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
}}

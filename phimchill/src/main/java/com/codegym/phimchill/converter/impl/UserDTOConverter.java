package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.IUserDTOConverter;
import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements IUserDTOConverter {
    public UserDTO converterToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public RegisterResponse converterRegister(User user) {
        RegisterResponse response = new RegisterResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}
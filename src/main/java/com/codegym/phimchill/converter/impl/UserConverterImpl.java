package com.codegym.phimchill.converter.impl;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    public UserDTO converterToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public RegisterDto converterRegister(User user) {
        RegisterDto response = new RegisterDto();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}

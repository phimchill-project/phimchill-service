package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterImpl implements UserConverter {
    public UserDto converterToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDTO = new UserDto();
//        BeanUtils.copyProperties(user, userDTO);
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setMember(user.isMember());
        userDTO.setRole(user.getRole().getName());
        return userDTO;
    }

    @Override
    public List<UserDto> converterToListDto(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList){
            userDtoList.add(converterToDTO(user));
        }
        return userDtoList;
    }

    @Override
    public RegisterDto converterRegister(User user) {
        RegisterDto response = new RegisterDto();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}

package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserConverter {
    UserDto converterToDTO(User user);
    List<UserDto> converterToListDto(List<User> userList);
    RegisterDto converterRegister(User user);
}

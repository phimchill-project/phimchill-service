package com.codegym.phimchill.converter;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.User;

public interface UserConverter {
    UserDto converterToDTO(User user);
    RegisterDto converterRegister(User user);
}

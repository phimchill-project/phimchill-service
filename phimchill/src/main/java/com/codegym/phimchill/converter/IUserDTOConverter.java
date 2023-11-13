package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;

public interface IUserDTOConverter {
    UserDTO converterToDTO(User user);
    RegisterResponse converterRegister(User user);
}
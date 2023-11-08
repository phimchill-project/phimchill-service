package com.codegym.phimchill.convert;

import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;

public interface IUserConvert   {
    public UserDTO convertToDTO(User user);
    public RegisterResponse convertRegister(User user);
}

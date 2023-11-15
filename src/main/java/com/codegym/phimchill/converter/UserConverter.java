package com.codegym.phimchill.converter;
<<<<<<< HEAD
=======

import com.codegym.phimchill.dto.RegisterDto;
>>>>>>> 8afa229d6c79178d59d395e4b061a2bf4afff784
import com.codegym.phimchill.dto.UserDTO;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.entity.User;

public interface UserConverter {
    UserDTO converterToDTO(User user);
    RegisterDto converterRegister(User user);
}

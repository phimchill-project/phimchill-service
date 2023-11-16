package com.codegym.phimchill.dto.payload.response;
import com.codegym.phimchill.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
<<<<<<< HEAD
    private UserDTO data;
=======
    private UserDto data;
>>>>>>> 7b7e16617f13882f2c94c2d7a7e39279891b5ff4
    private int statusCode;
    private String message;
}

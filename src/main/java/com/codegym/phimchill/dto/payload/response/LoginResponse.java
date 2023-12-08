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
    private UserDto data;
    private int statusCode;
    private String message;
    
}

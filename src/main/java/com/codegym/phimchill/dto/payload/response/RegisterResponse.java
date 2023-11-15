package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private RegisterDto data;
    private String message;
    private int statusCode;
}

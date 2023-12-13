package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.UserDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private UserDto data;

    private int statusCode;

    private String message;
}

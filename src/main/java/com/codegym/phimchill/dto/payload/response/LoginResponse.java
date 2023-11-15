package com.codegym.phimchill.dto.payload.response;
import com.codegym.phimchill.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private UserDTO userDTO;
    private int statusCode;
    private String message;
}

package com.codegym.phimchill.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\W).{8,}$", message = "Password should be valid")
    private String password;
}

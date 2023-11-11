package com.codegym.phimchill.dto.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @Email(message = "Email shoud valid")
    private String email;
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\W).{8,}$", message = "Password should valid")
    private String password;
}

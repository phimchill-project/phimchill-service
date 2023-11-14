package com.codegym.phimchill.dto.payload.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class LoginRequest {
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\W).{8,}$", message = "Password should be valid")
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

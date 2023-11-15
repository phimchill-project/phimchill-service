package com.codegym.phimchill.dto.payload.response;
import com.codegym.phimchill.dto.UserDTO;

public class LoginResponse {
    private UserDTO userDTO;
    private int statusCode;

    public int getStatuscode() {
        return statusCode;
    }

    public void setStatuscode(int statuscode) {
        this.statusCode = statuscode;
    }

    private String message;
    public LoginResponse() {
        this.userDTO = userDTO;
    }
    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

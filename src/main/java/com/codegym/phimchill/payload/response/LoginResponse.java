package com.codegym.phimchill.payload.response;

import com.codegym.phimchill.dto.UserDTO;

public class LoginResponse {
    private UserDTO userDTO;
    private int statuscode;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
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

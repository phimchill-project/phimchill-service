package com.codegym.phimchill.controller;
import com.codegym.phimchill.dto.payload.request.CheckEmailExist;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.payload.request.LoginRequest;
import com.codegym.phimchill.payload.request.RegisterRequest;
import com.codegym.phimchill.payload.response.LoginResponse;
import com.codegym.phimchill.payload.response.RegisterResponse;
import com.codegym.phimchill.payload.request.CheckEmailExist;
import com.codegym.phimchill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    @Autowired

    private IUserService iUserService ;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@Validated @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = iUserService.login(loginRequest);
        if (loginResponse.getUserDTO() == null) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest registerRequest) throws Exception {
        RegisterResponse response = iUserService.register(registerRequest);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/email-not-existion")
    public ResponseEntity<?> isEmailNotExist (@RequestBody CheckEmailExist checkEmailExist) {
        boolean response = iUserService.isEmailExist(checkEmailExist);
        if(response){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

}

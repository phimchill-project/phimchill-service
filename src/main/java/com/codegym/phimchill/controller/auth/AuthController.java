package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.LoginGoogleRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.security.JwtTokenProvider;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@Validated @RequestBody LoginRequest loginRequest) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateToken(authentication);
            UserDto userDto = userService.login(loginRequest);
            LoginResponse loginResponse = new LoginResponse();
            userDto.setToken(token);
            loginResponse.setData(userDto);
            loginResponse.setStatusCode(200);
            loginResponse.setMessage("login success");
            return ResponseEntity.ok(loginResponse);
        }catch (Exception ex) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage("Lỗi");
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PostMapping("/login-google")
    public ResponseEntity<?> login(@RequestBody LoginGoogleRequest loginGoogleRequest) {
        UserDto userDto = userService.loginGoogle(loginGoogleRequest);
        LoginResponse response;
        if (userDto != null) {
            response = LoginResponse.builder()
                    .data(userDto)
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .build();
        } else {
            response = LoginResponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Failed")
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest registerRequest) throws Exception {
        RegisterResponse response = userService.register(registerRequest);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/email-not-existion")
    public ResponseEntity<?> isEmailNotExist (@RequestBody EmailRequest emailRequest) {
        boolean response = userService.isEmailExist(emailRequest);
        if(response){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}

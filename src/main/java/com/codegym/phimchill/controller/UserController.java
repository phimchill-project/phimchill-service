package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        @PutMapping("/edit-email")
        public ResponseEntity<?> editEmail( @RequestBody EmailRequest emailRequest) {
                // Gọi service để xử lý logic
                boolean updated = userService.updateEmail(emailRequest.getEmail());
                if (updated) {
                        return ResponseEntity.ok("Email updated successfully");
                } else {
                        return ResponseEntity.notFound().build();
                }
        }

}

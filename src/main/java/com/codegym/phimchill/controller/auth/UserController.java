package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.PassRequest;
import com.codegym.phimchill.service.SecurityService;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private SecurityService securityService;
        @PutMapping("/edit-email")
        public ResponseEntity<?> editEmail( @RequestBody EmailRequest emailRequest,@RequestHeader("Authorization") String authToken) {
                // Gọi service để xử lý logic
                if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
                        return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
                }
                boolean updated = userService.updateEmail(emailRequest.getEmail());
                if (updated) {
                        return ResponseEntity.ok("Email updated successfully");
                } else {
                        return ResponseEntity.ok("Email updated fail");
                }

        }
        @PutMapping("/edit-password")
        public ResponseEntity<?> editPassword (@RequestBody PassRequest passRequest, @RequestHeader("Authorization") String authToken ){
                if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
                        return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
                }
                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                boolean updated = userService.updatePass( email,passRequest.getPass());
                if (updated) {
                        return ResponseEntity.ok("Pass updated successfully");
                } else {
                        return ResponseEntity.ok("Pass updated fail");
                }
        }
//        @PutMapping("/edit-password")
//        public ResponseEntity<?> editPassword (@RequestBody PassRequest passRequest,@RequestHeader("Authorization") String authToken ){
//                if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
//                        return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
//                }
//                boolean updated = userService.updatePass(passRequest.getPass());
//
//        }


}

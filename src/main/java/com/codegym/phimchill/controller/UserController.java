package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.security.JwtAuthFilter;
import com.codegym.phimchill.security.JwtTokenProvider;
import com.codegym.phimchill.service.SecurityService;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<?> editEmail(@RequestBody EmailRequest emailRequest, @RequestHeader("Authorization") String authToken) {
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

    //        @PutMapping("/edit-password")
//        public ResponseEntity<?> editPassword (@RequestBody PassRequest passRequest,@RequestHeader("Authorization") String authToken ){
//                if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
//                        return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
//                }
//                boolean updated = userService.updatePass(passRequest.getPass());
//
//        }
    @GetMapping("/favorite-movies")
    public ResponseEntity<?> getFavoriteMovies(@RequestHeader("Authorization") String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ListMovieResponse response = userService.getFavoriteMovies(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/favorite-tvseries")
    public ResponseEntity<?> getFavoriteTVseries(@RequestHeader("Authorization") String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ListTvSeriesResponse response = userService.getFavoriTeTVSeries(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Hoặc mã trạng thái phù hợp khác
        }
    }
    @DeleteMapping("/favorite-movies/{movieId}")
    public ResponseEntity<?> deleteFavoriteMovie(@RequestHeader("Authorization") String authToken, @PathVariable Long movieId) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ListMovieResponse response = userService.deleteFavoriteMovies(email, movieId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ListMovieResponse(new ArrayList<>(), e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
    @DeleteMapping("/favorite-tvseries/{tvSeriesId}")
    public ResponseEntity<?> deleteFavoriteTVSeries(@RequestHeader("Authorization") String authToken, @PathVariable Long tvSeriesId) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ListTvSeriesResponse response = userService.deleteFavoriteTVSeries(email, tvSeriesId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ListTvSeriesResponse(new ArrayList<>(), e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }



}

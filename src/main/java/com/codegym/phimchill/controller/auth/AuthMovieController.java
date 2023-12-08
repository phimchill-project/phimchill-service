package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.NewFilmRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ErrorResponse;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movie")
public class AuthMovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/new")
    public ResponseEntity<?> createNewMovie(@RequestHeader("Authorization") final String authToken,
            @RequestBody NewMovieRequest newMovieRequest) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }
        try {
            MovieResponse response = movieService.create(newMovieRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/check-exist")
    public ResponseEntity<?> isNotExist(@Validated @RequestBody MovieNameRequest movieNameRequest) {
        CheckMovieNameExistResponse response = movieService.isNotExist(movieNameRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
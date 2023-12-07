package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.MovieHistoryRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieHistoryResponse;
import com.codegym.phimchill.dto.payload.response.MovieHistoryResponse;
import com.codegym.phimchill.service.MovieHistoryService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies/history")
@CrossOrigin(value = "*", maxAge = 3600)
public class MovieHistoryController {
    @Autowired
    private MovieHistoryService movieHistoryService;

    @Autowired
    private SecurityService securityService;

    @PutMapping("/add")
    public ResponseEntity<MovieHistoryResponse> saveHistory(@RequestBody MovieHistoryRequest movieHistoryRequest, @RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            MovieHistoryResponse response = MovieHistoryResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            MovieHistoryResponse response = movieHistoryService.save(movieHistoryRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            MovieHistoryResponse response = MovieHistoryResponse.builder()
                    .data(null)
                    .message("Cannot save movie history")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/watched-movies")
    public ResponseEntity<ListMovieHistoryResponse> getWatchedMovies(@RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            ListMovieHistoryResponse response = ListMovieHistoryResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            ListMovieHistoryResponse response = movieHistoryService.getWatchedMovies();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ListMovieHistoryResponse response = ListMovieHistoryResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}

package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.FindMovieReponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.ErrorResponse;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(value = "*", maxAge = 3600)
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<MovieDto> MovieDtoList = movieService.findAll();
        return new ResponseEntity<>(MovieDtoList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewMovie(
            @RequestBody NewMovieRequest newMovieRequest) {
//        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
//            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
//        }
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

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingMovies() {
        List<UpcomingMoviesResponse> upcomingMovies = movieService.getUpcomingMovies();
        return ResponseEntity.ok(upcomingMovies);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getByName(@RequestParam(value = "name", required = true) String nameMovie) {
        MovieDto movieDto = movieService.findByName(nameMovie);
        FindMovieReponse response;
        if (movieDto != null){
            response = FindMovieReponse.builder()
                    .data(movieDto)
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .build();
        }else {
            response = FindMovieReponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Not found Movie")
                    .build();
        }
        return ResponseEntity.ok(response);
    }
}
package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(value = "*", maxAge = 3600)
public class MovieController {
    @Autowired
    private MovieService movieService;


    @Autowired
    private SecurityService securityService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<MovieDto> MovieDtoList = movieService.findAll();
        return new ResponseEntity<>(MovieDtoList, HttpStatus.OK);
    }
    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingMovies() {
       UpcomingMoviesResponse upcomingMovies = movieService.getUpcomingMovies();
        return ResponseEntity.ok(upcomingMovies);
    }
    @GetMapping("/blockbuster")
    public ResponseEntity<?>  getBlockbusterMoives(){
        ListMovieResponse movies = movieService.getMoviesByCategory(1L);
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/top-views")
    public ResponseEntity<?> getTopMoviesByViews() {
        ListMovieResponse movies = movieService.getTop10MoviesByViews();
        return ResponseEntity.ok(movies);
    }
}

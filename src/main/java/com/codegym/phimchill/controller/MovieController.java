package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(value = "*", maxAge = 3600)
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<MovieDto> MovieDtoList = movieService.findAll();
        return new ResponseEntity<>(MovieDtoList, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingMovies() {
        List<UpcomingMoviesResponse> upcomingMovies = movieService.getUpcomingMovies();
        return ResponseEntity.ok(upcomingMovies);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getMovieDetail(Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}

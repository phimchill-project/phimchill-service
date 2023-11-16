package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.MovieDto;
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
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<MovieDto> MovieDtoList = movieService.findAll();
        return new ResponseEntity<>(MovieDtoList, HttpStatus.OK);
    }
}

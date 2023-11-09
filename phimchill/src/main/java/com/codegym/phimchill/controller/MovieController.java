package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.service.IMovieService;
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
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<MovieDTO> movieDTOList = movieService.findAll();
        return new ResponseEntity<>(movieDTOList, HttpStatus.OK);
    }
}
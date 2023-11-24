package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.FindMovieReponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.SecurityService;
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
    private SecurityService securityService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<MovieDto> MovieDtoList = movieService.findAll();
        return new ResponseEntity<>(MovieDtoList, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingMovies() {
        ListMovieResponse upcomingMovies = movieService.getUpcomingMovies();
        return ResponseEntity.ok(upcomingMovies);
    }
    @GetMapping("/detail")
    public ResponseEntity<?> getMovieDetail(Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    @GetMapping("/blockbuster")
    public ResponseEntity<?>  getBlockbusterMoives(){
        ListMovieResponse movies = movieService.getMoviesSortedByIMDBAndDate();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/top-views")
    public ResponseEntity<?> getTopMoviesByViews() {
        ListMovieResponse movies = movieService.getTop10MoviesByViews();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-imdb")
    public ResponseEntity<?> getMoviesbyImbdTop() {
        ListMovieResponse movies = movieService.getMoviesbyImbdTop();
        return ResponseEntity.ok(movies);
    }
    @GetMapping("/search")
    public ResponseEntity<?> getByName(/*@RequestHeader("Authorization") final String authToken,*/ @RequestParam(value = "name", required = true) String nameMovie) {
        /*if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }*/
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

//    @GetMapping("/{movieId}/comments")
//    public ResponseEntity<ListMovieCommentResponse> getAllByMovieId(@PathVariable Long movieId){
//        try {
//            ListMovieCommentResponse response = movieService.getMovieCommentsById(movieId);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }catch (Exception e){
//            ListMovieCommentResponse response = new ListMovieCommentResponse();
//            response.setData(null);
//            response.setMessage("Cannot get comments by movie id "+ movieId);
//            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
}


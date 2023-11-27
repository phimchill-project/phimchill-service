package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.CategoryListResponse;
import com.codegym.phimchill.dto.payload.response.ErrorResponse;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategory() {
        try {
            CategoryListResponse response = categoryService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse response = new ErrorResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<ListMovieResponse> getAllMoviesByCategoryId(@PathVariable(name = "id") Long id) {
        try {
;            ListMovieResponse response = categoryService.findMoviesByCategoryId(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ListMovieResponse response = ListMovieResponse.builder()
                    .data(null)
                    .message("Cannot find movies by category id : " + e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/tv-series")
    public ResponseEntity<ListTvSeriesResponse> getAllTvSeriesByCategoryId(@PathVariable(name = "id") Long id) {
        try{
            ListTvSeriesResponse response = categoryService.findTvSeriesByCategoryId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ListTvSeriesResponse response = ListTvSeriesResponse.builder()
                    .data(null)
                    .message("Cannot find tv series by category id : " + e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

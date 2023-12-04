package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.MovieSubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private MovieCommentService movieCommentService;

    @Autowired
    private MovieSubCommentService movieSubCommentService;

    @GetMapping("/{id}/subcomments")
    public ResponseEntity<MovieCommentResponse> getSubCommentByCommentId(@PathVariable Long id){
        try {
            MovieCommentResponse response = movieSubCommentService.getSubCommentByCommentId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            MovieCommentResponse response = MovieCommentResponse.builder()
                    .data(null)
                    .message("Cannot find Comment id " + id)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

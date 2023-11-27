package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.MovieSubCommentService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/comment")
public class AuthCommentController {
    @Autowired
    private MovieCommentService movieCommentService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/movie-comment")
    public ResponseEntity<MovieCommentResponse> postMovieComment(@RequestBody MovieCommentRequest commentRequest, @RequestHeader("Authorization") final String authToken){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            MovieCommentResponse response = MovieCommentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            MovieCommentResponse response = movieCommentService.save(commentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            MovieCommentResponse response = MovieCommentResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(400)
                    .build();
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/movie-comment/{commentID}/subcomment")
    public ResponseEntity<MovieSubCommentResponse> postRepliedMovieComment(@RequestHeader("Authorization") final String authToken, @PathVariable Long commentID, @RequestBody MovieSubCommentRequest request) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            MovieSubCommentResponse response = MovieSubCommentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            MovieSubCommentResponse response = movieCommentService.saveSubComment(request, commentID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            MovieSubCommentResponse response = MovieSubCommentResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(400)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

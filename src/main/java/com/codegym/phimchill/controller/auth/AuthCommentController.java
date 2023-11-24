package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.RepliedMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.RepliedMovieCommentResponse;
import com.codegym.phimchill.entity.RepliedMovieComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.RepliedMovieCommentService;
import com.codegym.phimchill.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private RepliedMovieCommentService repliedMovieCommentService;

    @PostMapping("/movie-comment")
    public ResponseEntity<MovieCommentResponse> postMovieComment(@RequestBody MovieCommentRequest commentRequest){
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
    public ResponseEntity<RepliedMovieCommentResponse> postRepliedMovieComment(@RequestHeader("Authorization") final String authToken, @PathVariable Long commentID, @RequestBody RepliedMovieCommentRequest request) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            RepliedMovieCommentResponse response = RepliedMovieCommentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            RepliedMovieCommentResponse response = repliedMovieCommentService.save(request, commentID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            RepliedMovieCommentResponse response = RepliedMovieCommentResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(400)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

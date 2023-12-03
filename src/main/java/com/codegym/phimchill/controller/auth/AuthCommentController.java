package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.LikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.request.UnLikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieSubCommnetResponse;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.MovieSubCommentService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/comment")
public class AuthCommentController {
    @Autowired
    private MovieCommentService movieCommentService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private MovieSubCommentService movieSubCommentService;

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

    @PutMapping("/movie-comment/like")
    public ResponseEntity<TotalLikesMovieCommentResponse> likeMovieComment(@RequestBody LikeMovieCommentRequest likeMovieCommentRequest, @RequestHeader("Authorization") final String authToken ){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            TotalLikesMovieCommentResponse response = TotalLikesMovieCommentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            TotalLikesMovieCommentResponse response = movieCommentService.likeMovieComment(email, likeMovieCommentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TotalLikesMovieCommentResponse response = TotalLikesMovieCommentResponse.builder()
                    .data(null)
                    .message("Update increase like movie comment fail")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/movie-comment/unlike")
    public ResponseEntity<TotalLikesMovieCommentResponse> unLikeMovieComment(@RequestBody UnLikeMovieCommentRequest unLikeMovieCommentRequest, @RequestHeader("Authorization") final String authToken ){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            TotalLikesMovieCommentResponse response = TotalLikesMovieCommentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            TotalLikesMovieCommentResponse response = movieCommentService.unLikeMovieComment(email, unLikeMovieCommentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TotalLikesMovieCommentResponse response = TotalLikesMovieCommentResponse.builder()
                    .data(null)
                    .message("Update decrease like movie comment fail")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/movie-subcomment/like")
    public ResponseEntity<TotalLikesMovieSubCommnetResponse> likeMovieSubComment(@RequestBody LikeMovieCommentRequest likeMovieCommentRequest, @RequestHeader("Authorization") final String authToken ){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            TotalLikesMovieSubCommnetResponse response = TotalLikesMovieSubCommnetResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            TotalLikesMovieSubCommnetResponse response = movieSubCommentService.likeMovieSubComment(email, likeMovieCommentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TotalLikesMovieSubCommnetResponse response = TotalLikesMovieSubCommnetResponse.builder()
                    .data(null)
                    .message("Update increase like movie comment fail")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/movie-subcomment/unlike")
    public ResponseEntity<TotalLikesMovieSubCommnetResponse> unLikeMovieSubComment(@RequestBody UnLikeMovieCommentRequest unLikeMovieCommentRequest, @RequestHeader("Authorization") final String authToken ){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            TotalLikesMovieSubCommnetResponse response = TotalLikesMovieSubCommnetResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            TotalLikesMovieSubCommnetResponse response = movieSubCommentService.unLikeMovieSubComment(email, unLikeMovieCommentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            TotalLikesMovieSubCommnetResponse response = TotalLikesMovieSubCommnetResponse.builder()
                    .data(null)
                    .message("Update decrease like movie comment fail")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}

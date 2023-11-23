package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.service.MovieCommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/comment")
public class AuthCommentController {
    @Autowired
    private MovieCommentService movieCommentService;

    @PostMapping("/movie-comment")
    public ResponseEntity<MovieCommentResponse> postMovieComment(@RequestBody MovieCommentRequest commentRequest, HttpServletRequest request){
        try {
            User currentUser = (User) request.getSession().getAttribute("currentUser");
//            User user = new User();
//            user.setId(1L);
            MovieCommentResponse response = movieCommentService.saveMovieComment(commentRequest, currentUser);
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

}

package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.LikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.request.UnLikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieCommentResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.MovieSubCommentService;
import com.codegym.phimchill.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {
    @Autowired
    private MovieCommentRepository movieCommentRepository;

    @Autowired
    private MovieCommentConverter movieCommentConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieSubCommentService movieSubCommentService;

    @Override
    public MovieCommentResponse save(@NotNull MovieCommentRequest commentRequest) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findUserByEmail(email);
        List<User> listUserTagged = new ArrayList<>();
        if(commentRequest.getListUserTagged() != null){
            for(Long id : commentRequest.getListUserTagged()){
                User user = userRepository.findById(id).orElse(null);
                if (user != null){
                    listUserTagged.add(user);
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Movie movieWatching = movieRepository.findById(commentRequest.getMovieId()).orElseThrow(() -> new Exception("Cannot Find Movie To Save Comment"));
        MovieComment newComment = MovieComment.builder()
                .comment(commentRequest.getComment())
                .datePost(commentRequest.getDatePost())
                .movie(movieWatching)
                .userComment(currentUser)
                .listUserTaggedInComment(listUserTagged)
//                .userListLikeComment(new ArrayList<>())
                .build();
        MovieComment savedComment = movieCommentRepository.save(newComment);
        currentUser.getCommentsMovieList().add(savedComment);
        userRepository.save(currentUser);
        return MovieCommentResponse.builder()
                .data(movieCommentConverter.convertToDto(savedComment))
                .message("Save Movie Comment Success")
                .statusCode(201)
                .build();
    }

    @Override
    public MovieSubCommentResponse saveSubComment(MovieSubCommentRequest request, Long commentID) throws Exception {
        return movieSubCommentService.save(request, commentID);
    }

    @Override
    public TotalLikesMovieCommentResponse likeMovieComment(String email, LikeMovieCommentRequest likeMovieCommentRequest) throws Exception {
        User currentUser = userRepository.findUserByEmail(email);
        if(currentUser == null) {
            throw new Exception("Cannot find user to update increase totalLike");
        }
        MovieComment movieComment = movieCommentRepository.findById(likeMovieCommentRequest.getMovieCommentId()).orElseThrow(
                () -> new Exception("Cannot find movie comment id " + likeMovieCommentRequest.getMovieCommentId() + "to update increase totalLike")
        );
        movieComment.getUserListLikeComment().add(currentUser);
        movieComment.setTotalLike(movieComment.getTotalLike() + 1);
        MovieComment savedMovieComment = movieCommentRepository.save(movieComment);
        currentUser.getMovieCommentListUserLiked().add(savedMovieComment);
        userRepository.save(currentUser);
        return TotalLikesMovieCommentResponse.builder()
                .data(savedMovieComment.getTotalLike())
                .message("Update increase totallike success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public TotalLikesMovieCommentResponse unLikeMovieComment(String email, UnLikeMovieCommentRequest unLikeMovieCommentRequest) throws Exception {
        User currentUser = userRepository.findUserByEmail(email);
        if(currentUser == null) {
            throw new Exception("Cannot find user to update decrease totalLike");
        }
        MovieComment movieComment = movieCommentRepository.findById(unLikeMovieCommentRequest.getMovieCommentId()).orElseThrow(
                () -> new Exception("Cannot find movie comment id " + unLikeMovieCommentRequest.getMovieCommentId() + "to update decrease totalLike")
        );
        movieComment.getUserListLikeComment().remove(currentUser);
        movieComment.setTotalLike(movieComment.getTotalLike() - 1);
        MovieComment savedMovieComment = movieCommentRepository.save(movieComment);
        currentUser.getMovieCommentListUserLiked().remove(savedMovieComment);
        userRepository.save(currentUser);
        return TotalLikesMovieCommentResponse.builder()
                .data(savedMovieComment.getTotalLike())
                .message("Update decrease totallike success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
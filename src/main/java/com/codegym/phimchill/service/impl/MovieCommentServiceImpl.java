package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.MovieSubComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.MovieSubCommentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.MovieSubCommentService;
import com.codegym.phimchill.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
        Movie movieWatching = movieRepository.findById(commentRequest.getMovieId()).orElseThrow(() -> new Exception("Cannot Find Movie To Save Comment"));
        MovieComment newComment = MovieComment.builder()
                .comment(commentRequest.getComment())
                .datePost(commentRequest.getDatePost())
                .movie(movieWatching)
                .userComment(currentUser)
                .listUserTaggedInComment(listUserTagged)
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
}
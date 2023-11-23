package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieCommentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {
    @Autowired
    private MovieCommentRepository movieCommentRepository;

    @Autowired
    private MovieCommentConverter movieCommentConverter;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MovieCommentResponse saveMovieComment(@NotNull MovieCommentRequest commentRequest, User currentUser) throws Exception {
        currentUser = userRepository.findById(currentUser.getId()).orElse(null);
        MovieComment newComment = MovieComment.builder()
                .comment(commentRequest.getComment())
                .datePost(commentRequest.getDatePost())
                .movie(movieRepository.findById(commentRequest.getMovieID()).orElseThrow( () -> new Exception("Cannot Find Movie To Save Comment")))
                .user(currentUser)
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
}

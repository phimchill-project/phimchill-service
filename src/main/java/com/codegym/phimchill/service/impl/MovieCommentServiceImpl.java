package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.RepliedMovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.RepliedMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.RepliedMovieCommentResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.RepliedMovieComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.RepliedMovieCommentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieCommentService;
import com.codegym.phimchill.service.UserService;
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
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RepliedMovieCommentRepository repliedMovieCommentRepository;

    @Autowired
    private RepliedMovieCommentConverter repliedMovieCommentConverter;

    @Override
    public MovieCommentResponse save(@NotNull MovieCommentRequest commentRequest) throws Exception {
        User currentUser = repository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Movie movieWatching = movieRepository.findById(commentRequest.getMovieID()).orElseThrow(() -> new Exception("Cannot Find Movie To Save Comment"));
        MovieComment newComment = MovieComment.builder()
                .comment(commentRequest.getComment())
                .datePost(commentRequest.getDatePost())
                .movie(movieWatching)
                .user(currentUser)
                .build();
        MovieComment savedComment = movieCommentRepository.save(newComment);
        currentUser.getCommentsMovieList().add(savedComment);
        repository.save(currentUser);
        return MovieCommentResponse.builder()
                .data(movieCommentConverter.convertToDto(savedComment))
                .message("Save Movie Comment Success")
                .statusCode(201)
                .build();
    }

    @Override
    public RepliedMovieCommentResponse saveRepliedMovieComment(RepliedMovieCommentRequest request, Long commentID) throws Exception {
        User repliedUser = repository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User repliedToUser = repository.findById(request.getUserID()).orElseThrow(() -> new Exception("Cannot find id of replied to User"));
        MovieComment movieComment = movieCommentRepository.findById(commentID).orElseThrow(() -> new Exception("Cannot find movie comment in save RepliedMovieCommentResponse"));
        RepliedMovieComment newComment = RepliedMovieComment.builder()
                .comment(request.getComment())
                .movieComments(movieComment)
                .user(repliedUser)
                .repliedToUser(repliedToUser)
                .build();
        RepliedMovieComment savedComment = repliedMovieCommentRepository.save(newComment);
        repliedUser.getRepliedMovieCommentsList().add(savedComment);
        repository.save(repliedUser);
        return RepliedMovieCommentResponse.builder()
                .data(repliedMovieCommentConverter.converToDto(savedComment))
                .message("Save RepliedMovieComment Success")
                .statusCode(201)
                .build();
    }
}
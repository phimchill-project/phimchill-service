package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.RepliedMovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.RepliedMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.RepliedMovieCommentResponse;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.RepliedMovieComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.RepliedMovieCommentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.RepliedMovieCommentService;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RepliedMovieCommentServiceImpl implements RepliedMovieCommentService {
    @Autowired
    private RepliedMovieCommentRepository repliedMovieCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieCommentRepository movieCommentRepository;
    @Autowired
    private RepliedMovieCommentConverter repliedMovieCommentConverter;

    @Override
    public RepliedMovieCommentResponse save(RepliedMovieCommentRequest request, Long commentID) throws Exception {
        User repliedUser = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User repliedToUser = userRepository.findById(request.getUserID()).orElseThrow(() -> new Exception("Cannot find id of replied to User"));
        MovieComment movieComment = movieCommentRepository.findById(commentID).orElseThrow(() -> new Exception("Cannot find movie comment in save RepliedMovieCommentResponse"));
        RepliedMovieComment newComment = RepliedMovieComment.builder()
                .comment(request.getComment())
                .movieComments(movieComment)
                .user(repliedUser)
                .repliedToUser(repliedToUser)
                .build();
        RepliedMovieComment savedComment = repliedMovieCommentRepository.save(newComment);
        repliedUser.getRepliedMovieCommentsList().add(savedComment);
        userRepository.save(repliedUser);
        return RepliedMovieCommentResponse.builder()
                .data(repliedMovieCommentConverter.converToDto(savedComment))
                .message("Save RepliedMovieComment Success")
                .statusCode(201)
                .build();
    }
}


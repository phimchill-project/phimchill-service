package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.MovieSubComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieSubCommentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieSubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieSubCommentServiceImpl implements MovieSubCommentService {
    @Autowired
    private MovieSubCommentRepository movieSubCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieCommentRepository movieCommentRepository;
    @Autowired
    private MovieSubCommentConverter movieSubCommentConverter;

    @Override
    public MovieSubCommentResponse save(MovieSubCommentRequest request, Long commentID) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userComment = userRepository.findUserByEmail(email);
        List<User> listUserTagged = null;
        if(request.getListUserTagged() != null){
            for(Long id : request.getListUserTagged()){
                User user = userRepository.findById(id).orElse(null);
                if (user != null){
                    listUserTagged.add(user);
                }
            }
        }
        MovieComment movieComment = movieCommentRepository.findById(commentID).orElseThrow(() -> new Exception("Cannot find movie comment in save RepliedMovieCommentResponse"));
        MovieSubComment newComment = MovieSubComment.builder()
                .comment(request.getComment())
                .datePost(request.getDatePost())
                .movieComments(movieComment)
                .subCommentUser(userComment)
                .listUserTaggedInSubComment(listUserTagged)
                .build();
        MovieSubComment savedComment = movieSubCommentRepository.save(newComment);
        userComment.getMovieSubCommentsList().add(savedComment);
        userRepository.save(userComment);
        if(listUserTagged != null){
            for(User user : listUserTagged){
                user.getListSubCommnetTaggedIn().add(newComment);
                userRepository.save(user);
            }
        }
        return MovieSubCommentResponse.builder()
                .data(movieSubCommentConverter.converToDto(savedComment))
                .message("Save Sub Comment Success")
                .statusCode(201)
                .build();
    }
}


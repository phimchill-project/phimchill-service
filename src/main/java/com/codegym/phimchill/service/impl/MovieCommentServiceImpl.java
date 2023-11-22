package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.service.MovieCommentService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCommentServiceImpl implements MovieCommentService {
    @Autowired
    private MovieCommentRepository movieCommentRepository;

    @Autowired
    private MovieCommentConverter movieCommentConverter;


    @Override
    public MovieCommentResponse saveMovieComment(@NotNull MovieCommentRequest commentRequest) {
        MovieComment newComment = movieCommentConverter.convertToEntity(commentRequest);
        return null;
    }
}

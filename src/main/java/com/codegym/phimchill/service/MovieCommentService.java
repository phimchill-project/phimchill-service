package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;

public interface MovieCommentService {
    MovieCommentResponse save(MovieCommentRequest commentRequest) throws Exception;

    MovieSubCommentResponse saveSubComment(MovieSubCommentRequest request, Long commentID) throws Exception;
}

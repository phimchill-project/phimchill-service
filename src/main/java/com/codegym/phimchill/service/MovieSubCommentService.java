package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;

public interface MovieSubCommentService {
    MovieSubCommentResponse save(MovieSubCommentRequest request, Long commentID) throws Exception;

    MovieCommentResponse getSubCommentByCommentId(Long id) throws Exception;
}

package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;

public interface MovieCommentService {
    MovieCommentResponse saveMovieComment(MovieCommentRequest commentRequest);
}

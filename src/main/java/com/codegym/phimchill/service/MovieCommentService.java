package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.entity.User;

public interface MovieCommentService {
    MovieCommentResponse saveMovieComment(MovieCommentRequest commentRequest, User currentUser) throws Exception;
}

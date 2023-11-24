package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.RepliedMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.RepliedMovieCommentResponse;
import com.codegym.phimchill.entity.User;

public interface MovieCommentService {
    MovieCommentResponse save(MovieCommentRequest commentRequest) throws Exception;

    RepliedMovieCommentResponse saveRepliedMovieComment(RepliedMovieCommentRequest request, Long commentID) throws Exception;
}

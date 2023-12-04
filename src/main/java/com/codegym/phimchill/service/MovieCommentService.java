package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.LikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.request.UnLikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieCommentResponse;

public interface MovieCommentService {
    MovieCommentResponse save(MovieCommentRequest commentRequest) throws Exception;

    MovieSubCommentResponse saveSubComment(MovieSubCommentRequest request, Long commentID) throws Exception;

    TotalLikesMovieCommentResponse likeMovieComment(String email, LikeMovieCommentRequest likeMovieCommentRequest) throws Exception;

    TotalLikesMovieCommentResponse unLikeMovieComment(String email, UnLikeMovieCommentRequest unLikeMovieCommentRequest) throws Exception;
}

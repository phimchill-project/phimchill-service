package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.LikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.request.UnLikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieSubCommnetResponse;

public interface MovieSubCommentService {
    MovieSubCommentResponse save(MovieSubCommentRequest request, Long commentID) throws Exception;

    MovieCommentResponse getSubCommentByCommentId(Long id) throws Exception;

    TotalLikesMovieSubCommnetResponse likeMovieSubComment(String email, LikeMovieCommentRequest likeMovieCommentRequest) throws Exception;

    TotalLikesMovieSubCommnetResponse unLikeMovieSubComment(String email, UnLikeMovieCommentRequest unLikeMovieCommentRequest) throws Exception;
}

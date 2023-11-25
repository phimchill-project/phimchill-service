package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.RepliedMovieCommentDto;
import com.codegym.phimchill.dto.payload.request.RepliedMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.RepliedMovieCommentResponse;

public interface RepliedMovieCommentService {
    RepliedMovieCommentResponse save(RepliedMovieCommentRequest request, Long commentID) throws Exception;

}

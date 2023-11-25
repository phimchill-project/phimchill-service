package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.entity.MovieComment;

public interface MovieCommentConverter {
    MovieCommentDto convertToDto(MovieComment savedComment);
}

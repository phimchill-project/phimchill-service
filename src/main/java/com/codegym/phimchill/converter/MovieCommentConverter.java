package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.entity.MovieComment;

public interface MovieCommentConverter {
    MovieComment convertToEntity(MovieCommentRequest commentRequest);
}

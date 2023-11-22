package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.entity.MovieComment;
import org.springframework.stereotype.Component;

@Component
public class MovieCommentConverterImpl implements MovieCommentConverter {

    @Override
    public MovieComment convertToEntity(MovieCommentRequest commentRequest) {
        return null;
    }
}

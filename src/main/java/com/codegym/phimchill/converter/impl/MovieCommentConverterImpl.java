package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.entity.MovieComment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MovieCommentConverterImpl implements MovieCommentConverter {

    @Override
    public MovieCommentDto convertToDto(MovieComment movieComment) {
        MovieCommentDto movieDTO = new MovieCommentDto();
        BeanUtils.copyProperties(movieComment, movieDTO);
        return movieDTO;
    }
}

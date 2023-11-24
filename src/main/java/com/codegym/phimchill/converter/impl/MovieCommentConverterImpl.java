package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.RepliedMovieCommentConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieCommentRequest;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.RepliedMovieComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieCommentConverterImpl implements MovieCommentConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private RepliedMovieCommentConverter repliedMovieCommentConverter;

    @Override
    public MovieCommentDto convertToDto(MovieComment movieComment) {
        MovieCommentDto response = MovieCommentDto.builder()
                .id(movieComment.getId())
                .comment(movieComment.getComment())
                .movie(movieConverter.convertToDTO(movieComment.getMovie()))
                .userDto(userConverter.converterToDTO(movieComment.getUser()))
                .build();
        if(movieComment.getRepliedMovieCommentsList() == null){
            response.setRepliedMovieCommentDtoList(null);
            return response;
        }
        response.setRepliedMovieCommentDtoList(repliedMovieCommentConverter.converToDtoList(movieComment.getRepliedMovieCommentsList()));
        return response;
    }
}

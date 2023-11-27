package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.entity.MovieComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieCommentConverterImpl implements MovieCommentConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private MovieSubCommentConverter movieSubCommentConverter;

    @Override
    public MovieCommentDto convertToDto(MovieComment movieComment) {
        MovieCommentDto response = MovieCommentDto.builder()
                .id(movieComment.getId())
                .comment(movieComment.getComment())
                .datePost(movieComment.getDatePost())
                .movieId(movieConverter.convertToDTO(movieComment.getMovie()).getId())
                .userDto(userConverter.converterToDTO(movieComment.getUserComment()))
                .build();
        if(movieComment.getMovieSubCommentsList() == null){
            response.setSubCommentDtoList(null);
        }else {
            response.setSubCommentDtoList(movieSubCommentConverter.converToDtoList(movieComment.getMovieSubCommentsList()));
        }
        if(movieComment.getListUserTaggedInComment() == null){
            response.setListUserTagged(null);
        }else {
            response.setListUserTagged(userConverter.converterToListDto(movieComment.getListUserTaggedInComment()));
        }
        return response;
    }
}

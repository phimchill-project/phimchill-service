package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieSubCommentDto;
import com.codegym.phimchill.entity.MovieSubComment;

import java.util.List;

public interface MovieSubCommentConverter {
    MovieSubCommentDto converToDto(MovieSubComment repliedMovieComment);

    List<MovieSubCommentDto> converToDtoList(List<MovieSubComment> repliedMovieCommentList);
}

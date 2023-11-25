package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.RepliedMovieCommentDto;
import com.codegym.phimchill.entity.RepliedMovieComment;

import java.util.List;

public interface RepliedMovieCommentConverter {
    RepliedMovieCommentDto converToDto(RepliedMovieComment repliedMovieComment);

    List<RepliedMovieCommentDto> converToDtoList(List<RepliedMovieComment> repliedMovieCommentList);
}

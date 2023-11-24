package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.RepliedMovieCommentConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.RepliedMovieCommentDto;
import com.codegym.phimchill.entity.RepliedMovieComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepliedMovieCommentConverterImpl implements RepliedMovieCommentConverter {
    @Autowired
    private UserConverter userConverter;

    @Override
    public RepliedMovieCommentDto converToDto(RepliedMovieComment repliedMovieComment) {
            RepliedMovieCommentDto response = new RepliedMovieCommentDto();
            response.setId(repliedMovieComment.getId());
            response.setComment(repliedMovieComment.getComment());
            response.setUserDtoComment(userConverter.converterToDTO(repliedMovieComment.getRepliedToUser()));
            response.setUserDtoSubComment(userConverter.converterToDTO(repliedMovieComment.getUser()));
            response.setMovieCommentId(repliedMovieComment.getMovieComments().getId());
            return response;
    }

    @Override
    public List<RepliedMovieCommentDto> converToDtoList(List<RepliedMovieComment> repliedMovieCommentList) {
        List<RepliedMovieCommentDto> response = new ArrayList<>();
        for (RepliedMovieComment repliedMovieComment : repliedMovieCommentList){
            response.add(converToDto(repliedMovieComment));
        }
        return response;
    }
}

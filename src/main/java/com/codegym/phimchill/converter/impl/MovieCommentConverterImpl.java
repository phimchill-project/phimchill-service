package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .totalLike(movieComment.getTotalLike())
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
        if(movieComment.getUserListLikeComment() == null){
            response.setListUserIdLiked(null);
            return response;
        }else {
            List<Long> listUserIdLiked = new ArrayList<>();
            for (User user : movieComment.getUserListLikeComment()){
                listUserIdLiked.add(user.getId());
            }
            response.setListUserIdLiked(listUserIdLiked);
        }
        return response;
    }

    @Override
    public List<MovieCommentDto> convertToDtoList(List<MovieComment> movieCommentList) {
        List<MovieCommentDto> movieCommentDtoList = new ArrayList<>();
        for (MovieComment movieComment : movieCommentList){
            movieCommentDtoList.add(convertToDto(movieComment));
        }
        return movieCommentDtoList;
    }
}

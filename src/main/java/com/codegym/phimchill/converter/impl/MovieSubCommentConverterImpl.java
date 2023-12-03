package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieSubCommentDto;
import com.codegym.phimchill.entity.MovieSubComment;
import com.codegym.phimchill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSubCommentConverterImpl implements MovieSubCommentConverter {
    @Autowired
    private UserConverter userConverter;

    @Override
    public MovieSubCommentDto converToDto(MovieSubComment movieSubComment) {
        MovieSubCommentDto response = new MovieSubCommentDto();
        response.setId(movieSubComment.getId());
        response.setComment(movieSubComment.getComment());
        response.setMovieCommentId(movieSubComment.getMovieComments().getId());
        response.setUserDtoComment(userConverter.converterToDTO(movieSubComment.getSubCommentUser()));
        if(movieSubComment.getUserListLikeSubComment() != null){
            List<Long> listUserIdLiked = new ArrayList<>();
            for (User user : movieSubComment.getUserListLikeSubComment()){
                listUserIdLiked.add(user.getId());
            }
            response.setListUserIdLiked(listUserIdLiked);
        }else {
            response.setListUserIdLiked(null);
        }
        response.setTotalLike(movieSubComment.getTotalLike());
        if (movieSubComment.getListUserTaggedInSubComment() == null) {
            response.setListUserTagged(null);
            return response;
        }
        response.setListUserTagged(userConverter.converterToListDto(movieSubComment.getListUserTaggedInSubComment()));
        return response;
    }

    @Override
    public List<MovieSubCommentDto> converToDtoList(List<MovieSubComment> movieSubCommentList) {
        List<MovieSubCommentDto> response = new ArrayList<>();
        for (MovieSubComment movieSubComment : movieSubCommentList) {
            response.add(converToDto(movieSubComment));
        }
        return response;
    }
}

package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieHistoryConverter;
import com.codegym.phimchill.dto.MovieHistoryDto;
import com.codegym.phimchill.dto.MovieHistoryWithMovieDetailDto;
import com.codegym.phimchill.entity.MovieHistory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieHistoryConverterImpl implements MovieHistoryConverter {


    @Override
    public MovieHistoryDto convertToDto(MovieHistory movieHistory) {
        return MovieHistoryDto.builder()
                .id(movieHistory.getId())
                .duration(movieHistory.getDuration())
                .build();
    }

    @Override
    public List<MovieHistoryDto> convertToDtoList(List<MovieHistory> movieHistories) {
        List<MovieHistoryDto> movieHistoryDtoList = new ArrayList<>();
        for (MovieHistory movieHistory : movieHistories ) {
            movieHistoryDtoList.add(convertToDto(movieHistory));
        }
        return movieHistoryDtoList;
    }
}

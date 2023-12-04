package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieHistoryConverter;
import com.codegym.phimchill.dto.MovieHistoryDto;
import com.codegym.phimchill.entity.MovieHistory;
import org.springframework.stereotype.Component;

@Component
public class MovieHistoryConverterImpl implements MovieHistoryConverter {


    @Override
    public MovieHistoryDto convertToDto(MovieHistory movieHistory) {
        return MovieHistoryDto.builder()
                .id(movieHistory.getId())
                .duration(movieHistory.getDuration())
                .build();
    }
}

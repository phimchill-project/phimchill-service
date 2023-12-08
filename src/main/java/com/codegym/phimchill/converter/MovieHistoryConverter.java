package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieHistoryDto;
import com.codegym.phimchill.dto.MovieHistoryWithMovieDetailDto;
import com.codegym.phimchill.entity.MovieHistory;

import java.util.List;

public interface MovieHistoryConverter {
    MovieHistoryDto convertToDto(MovieHistory movieHistory);
    List<MovieHistoryDto> convertToDtoList(List<MovieHistory> movieHistories);
}

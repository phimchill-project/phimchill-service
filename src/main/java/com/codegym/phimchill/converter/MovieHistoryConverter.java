package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieHistoryDto;
import com.codegym.phimchill.entity.MovieHistory;

public interface MovieHistoryConverter {
    MovieHistoryDto convertToDto(MovieHistory movieHistory);
}

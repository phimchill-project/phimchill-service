package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.TVSeriesHistoryConverter;
import com.codegym.phimchill.dto.MovieHistoryDto;
import com.codegym.phimchill.dto.TVSeriesHistoryWithDetailDto;
import com.codegym.phimchill.entity.MovieHistory;
import com.codegym.phimchill.entity.TVSeriesHistory;
import org.springframework.stereotype.Component;

@Component
public class TVSeriesHistoryConverterImpl implements TVSeriesHistoryConverter {
    @Override
    public TVSeriesHistoryWithDetailDto convertToDto(TVSeriesHistory tvSeriesHistory) {
        return TVSeriesHistoryWithDetailDto.builder()
                .tvseriesImg(tvSeriesHistory.getTvSeries().getImage())
                .tvseriesName(tvSeriesHistory.getTvSeries().getName())
                .build();
    }
}

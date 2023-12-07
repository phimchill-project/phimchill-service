package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.TVSeriesHistoryWithDetailDto;
import com.codegym.phimchill.entity.TVSeriesHistory;

public interface TVSeriesHistoryConverter {
    TVSeriesHistoryWithDetailDto convertToDto(TVSeriesHistory tvSeriesHistory);
}

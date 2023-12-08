package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.response.ListTVSeriesHistoryResponse;

public interface TVSeriesHistoryService {
    ListTVSeriesHistoryResponse getWatchedTVSeries() throws Exception;
}

package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.dto.TVSeriesHistoryWithDetailDto;
import com.codegym.phimchill.dto.payload.response.ListTVSeriesHistoryResponse;
import com.codegym.phimchill.entity.TVSeriesHistory;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.TVSeriesHistoryRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.TVSeriesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TVSeriesHistoryServiceImpl implements TVSeriesHistoryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TVSeriesHistoryRepository tvSeriesHistoryRepository;

    @Override
    public ListTVSeriesHistoryResponse getWatchedTVSeries() throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found with email: " + email);
        }

        List<TVSeriesHistory> watchedTVSeries = tvSeriesHistoryRepository.findTVSeriesHistoriesByUser_Id(user.getId());
        if (watchedTVSeries.isEmpty()) {
            throw new Exception("TVSeries history is empty");
        }
        List<TVSeriesHistoryWithDetailDto> data = new ArrayList<>();
        for (TVSeriesHistory tvSeriesHistory : watchedTVSeries) {
            data.add(TVSeriesHistoryWithDetailDto.builder()
                    .tvseriesImg(tvSeriesHistory.getTvSeries().getImage())
                    .tvseriesName(tvSeriesHistory.getTvSeries().getName())
                    .duration(tvSeriesHistory.getDuration())
                    .build());
        }
        return ListTVSeriesHistoryResponse.builder()
                .data(data)
                .message("Retrieved watched tvseries successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}

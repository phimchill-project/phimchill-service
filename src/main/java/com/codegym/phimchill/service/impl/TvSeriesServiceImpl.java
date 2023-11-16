package com.codegym.phimchill.service.impl;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.repository.TvSeriesPagingRepository;
import com.codegym.phimchill.repository.TvSeriesRepository;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TvSeriesServiceImpl implements TvSeriesService {
    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @Autowired
    private TvSeriesPagingRepository tvSeriesPagingRepository;

    @Override
    public NewMovieResponse create(NewMovieRequest newTvSeriesRequest) {

        return null;
    }

    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest tvSeriesNameRequest) {
        Optional<TVSeries> tvSeries = Optional.ofNullable(tvSeriesRepository.findByName(tvSeriesNameRequest.getName()));
        if(tvSeries.isEmpty()){
            return CheckMovieNameExistResponse.builder()
                    .data(true)
                    .statusCode(200)
                    .message("TvSeries is not exist")
                    .build();
        }
        return  CheckMovieNameExistResponse.builder()
                .data(false)
                .statusCode(302 )
                .message("TvSeries is not exist")
                .build();
    }
}

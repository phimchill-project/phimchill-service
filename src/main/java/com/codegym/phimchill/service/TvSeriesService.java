package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;

public interface TvSeriesService {
    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
}

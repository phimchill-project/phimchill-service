package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;

import java.util.List;

public interface TvSeriesService {
    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    List<TvSeriesDto> getTop10ByImdb();

    List<TvSeriesDto> getTop10Newest();

    List<TvSeriesDto> getTop10FavoriteList(Long user_id);

    TvSeriesDto findByName(String nameMovie);

    ListTvSeriesResponse findTvSeriesByCategoryId(Long id) throws Exception;
    String addFavoriteList(Long user_id, Long tvSeries_id);
}

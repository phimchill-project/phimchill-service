package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;

import java.util.List;
import java.util.Optional;

public interface TvSeriesService {
    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    List<TvSeriesDto> getTop10ByImdb();
    List<TvSeriesDto> getTop10Newest();

    TvSeriesDto findByName(String nameMovie);
    List<Optional<TvSeriesDto>> findManyTvSeriesByName(String nameMovive);

    ListTvSeriesResponse findTvSeriesByCategoryId(Long id) throws Exception;
}

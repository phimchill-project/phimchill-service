package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.NewFilmRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.UpdateFilmRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;

import java.util.List;
import java.util.Optional;

public interface TvSeriesService {
    boolean create(NewFilmRequest newTvSeriesRequest);

    boolean update(UpdateFilmRequest updateFilmRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    List<TvSeriesDto> getTop10ByImdb();

    List<TvSeriesDto> getTop10Newest();

    List<TvSeriesDto> getTop10FavoriteList(Long user_id);

    TvSeriesDto findByName(String nameMovie);
    List<Optional<TvSeriesDto>> findManyTvSeriesByName(String nameMovive);

    ListTvSeriesResponse findTvSeriesByCategoryId(Long id) throws Exception;
    String addFavoriteList(Long user_id, Long tvSeries_id);
    List<TvSeriesDto> findAll();
    void deleteTVSeries(Long id);
    void restoreTVSeries(Long id);
}

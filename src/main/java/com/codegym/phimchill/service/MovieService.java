package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import java.util.List;

public interface MovieService {

    UpcomingMoviesResponse getUpcomingMovies();

    List<MovieDto> findAll();

    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    ListMovieResponse getMoviesByCategory(Long id) ;
    ListMovieResponse getTop10MoviesByViews();



}

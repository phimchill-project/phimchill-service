package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import java.util.List;

public interface MovieService {

   List<UpcomingMoviesResponse> getUpcomingMovies();

    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest);
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
    List<MovieDto> getTop10ByImdb();
}

package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.Movie;

import java.util.List;

public interface MovieService {

   List<UpcomingMoviesResponse> getUpcomingMovies();

    List<MovieDto> findAll();

    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    MovieDto getMovieById(Long id);
}

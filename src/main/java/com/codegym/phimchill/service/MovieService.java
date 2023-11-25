package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.entity.Movie;

import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import java.util.List;

public interface MovieService {
    ListMovieResponse getUpcomingMovies();
    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
    ListMovieResponse getMoviesByCategory(Long id) ;
    ListMovieResponse getTop10MoviesByViews();
    List<MovieDto> getTop10ByImdb();
    MovieDto findByName(String nameMovie);
    ListMovieResponse getMoviesSortedByIMDBAndDate();
    ListMovieResponse getMoviesbyImbdTop();
    MovieDto getMovieById(Long id);
}

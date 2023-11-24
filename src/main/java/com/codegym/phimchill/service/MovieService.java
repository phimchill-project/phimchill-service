package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.entity.Movie;

import java.util.List;

public interface MovieService {
    List<UpcomingMoviesResponse> getUpcomingMovies();
    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
    List<MovieDto> getTop10ByImdb();
    MovieDto findByName(String nameMovie);
    List<Movie> findByCategoryId(Long id) throws Exception;
    ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception;
}

package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewFilmRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
//import com.codegym.phimchill.entity.MovieHistory;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    ListMovieResponse getUpcomingMovies();
    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
    ListMovieResponse getMoviesByCategory(Long id) ;
    ListMovieResponse getTop10MoviesByViews();
    List<MovieDto> getTop10ByImdb();
    MovieDto findByName(String nameMovie);
    List<Optional<MovieDto>> findMoviesByName(String nameMovive);
    ListMovieResponse getMoviesSortedByIMDBAndDate();
    ListMovieResponse getMoviesbyImbdTop();
    MovieDto getMovieById(Long id);
    ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception;
    ListMovieResponse findMoviesByCategoryId(Long id) throws Exception;
    MovieHistoryResponse DurationByMovieId(Long movieId) throws Exception;
    ListMovieResponse updateMovie(MovieDto movieDto) throws Exception;
}

package com.codegym.phimchill.service;

<<<<<<< HEAD
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.entity.Movie;
=======
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
>>>>>>> a8944ae78980ff5f7ebebaee44d61d32d53e282d

import java.util.List;

public interface MovieService {
<<<<<<< HEAD
    List<UpcomingMoviesResponse> getUpcomingMovies();
    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
=======

    ListMovieResponse getUpcomingMovies();
    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
    ListMovieResponse getMoviesByCategory(Long id) ;
    ListMovieResponse getTop10MoviesByViews();

>>>>>>> a8944ae78980ff5f7ebebaee44d61d32d53e282d
    List<MovieDto> getTop10ByImdb();
    MovieDto findByName(String nameMovie);
<<<<<<< HEAD
    List<Movie> findByCategoryId(Long id) throws Exception;
    ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception;
=======
    ListMovieResponse getMoviesSortedByIMDBAndDate();
    ListMovieResponse getMoviesbyImbdTop();

    MovieDto getMovieById(Long id);
>>>>>>> a8944ae78980ff5f7ebebaee44d61d32d53e282d
}

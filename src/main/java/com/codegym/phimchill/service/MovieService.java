package com.codegym.phimchill.service;

<<<<<<< HEAD
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
=======
import com.codegym.phimchill.dto.TvSeriesDto;
>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import java.util.List;

public interface MovieService {

    UpcomingMoviesResponse getUpcomingMovies();

    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest);
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

<<<<<<< HEAD
    ListMovieResponse getMoviesByCategory(Long id) ;
    ListMovieResponse getTop10MoviesByViews();



=======
    List<MovieDto> getTop10ByImdb();

    MovieDto findByName(String nameMovie);
>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
}

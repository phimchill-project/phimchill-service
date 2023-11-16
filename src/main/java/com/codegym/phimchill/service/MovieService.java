package com.codegym.phimchill.service;
<<<<<<< HEAD
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
=======
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
>>>>>>> 7b7e16617f13882f2c94c2d7a7e39279891b5ff4

import java.util.List;

public interface MovieService {
<<<<<<< HEAD
    List<MovieDTO> findAll();

   List<UpcomingMoviesResponse> getUpcomingMovies();
=======
    List<MovieDto> findAll();

    NewMovieResponse create(NewMovieRequest newTvSeriesRequest);

    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);
>>>>>>> 7b7e16617f13882f2c94c2d7a7e39279891b5ff4
}

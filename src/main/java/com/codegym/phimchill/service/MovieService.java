package com.codegym.phimchill.service;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAll();

   List<UpcomingMoviesResponse> getUpcomingMovies();
}

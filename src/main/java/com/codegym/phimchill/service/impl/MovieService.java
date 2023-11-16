package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements com.codegym.phimchill.service.MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieDTOConvert;

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDTO> movieDTOList = movieDTOConvert.convertToListDTO(movieList);
        return movieDTOList;
    }

    @Override
    public List<UpcomingMoviesResponse> getUpcomingMovies() {
        List<Movie> upcomingMovieList = movieRepository.findUnreleasedMovies();
        return movieDTOConvert.convertToUpcomingMoviesResponse(upcomingMovieList);
    }

}

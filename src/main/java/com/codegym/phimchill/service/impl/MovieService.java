package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements com.codegym.phimchill.service.MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieDTOConvert;

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> movieDTOList = movieDTOConvert.convertToListDTO(movieList);
        return movieDTOList;
    }

    @Override
    public NewMovieResponse create(NewMovieRequest newTvSeriesRequest) {
        return null;
    }

    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest) {
        return null;
    }

    @Override
    public List<UpcomingMoviesResponse> getUpcomingMovies() {
        List<Movie> upcomingMovieList = movieRepository.findUnreleasedMovies();
        return movieDTOConvert.convertToUpcomingMoviesResponse(upcomingMovieList);
    }

}
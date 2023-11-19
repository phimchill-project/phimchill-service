package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverterImpl implements MovieConverter {
    @Override
    public MovieDto convertToDTO(Movie movie) {
        MovieDto movieDTO = new MovieDto();
        BeanUtils.copyProperties(movie, movieDTO);
        return movieDTO;
    }

    @Override
    public List<MovieDto> convertToListDTO(List<Movie> movies) {
        List<MovieDto> listDTO = new ArrayList<>();
        for (Movie movie: movies
        ) {

            listDTO.add(convertToDTO(movie));
        }
        return listDTO;
    }

    @Override
    public List<UpcomingMoviesResponse> convertToUpcomingMoviesResponse(List<Movie> movie) {
        return movie.stream()
                .map(this::convertMovieToUpcomingMoviesResponse)
                .collect(Collectors.toList());
    }
      private UpcomingMoviesResponse convertMovieToUpcomingMoviesResponse(Movie movie) {
        UpcomingMoviesResponse response = new UpcomingMoviesResponse();
        BeanUtils.copyProperties(movie,response);
        return response;
    }
}

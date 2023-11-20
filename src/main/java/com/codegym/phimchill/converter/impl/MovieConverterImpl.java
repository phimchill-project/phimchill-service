package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.UpcomingMovieDto;
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
    public List<UpcomingMovieDto> convertToUpcomingMoviesResponse(List<Movie> movies) {
        return movies.stream()
                .map(this::convertToUpcomingMovieDTO)
                .collect(Collectors.toList());
    }
    private UpcomingMovieDto convertToUpcomingMovieDTO(Movie movie) {
        UpcomingMovieDto dto = new UpcomingMovieDto();
        BeanUtils.copyProperties(movie,dto);
        return dto;
    }

    @Override
    public MovieDto convertToMovieDTO(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setViews(movie.getViews());
        return dto;
    }
    public List<MovieDto> convertToMovieDTOList(List<Movie> movies) {
        return movies.stream()
                .map(this::convertToMovieDTO)
                .collect(Collectors.toList());
    }

}

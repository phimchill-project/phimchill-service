package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.UpcomingMovieDto;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface MovieConverter {

     List<UpcomingMovieDto> convertToUpcomingMoviesResponse(List<Movie> movie);

     MovieDto convertToDTO(Movie movie);

     List<MovieDto> convertToListDTO(List<Movie> movies);
     MovieDto convertToMovieDTO(Movie movie);
     List<MovieDto> convertToMovieDTOList(List<Movie> movies);
}

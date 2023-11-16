package com.codegym.phimchill.converter;
<<<<<<< HEAD
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
=======
import com.codegym.phimchill.dto.MovieDto;
>>>>>>> 7b7e16617f13882f2c94c2d7a7e39279891b5ff4
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface MovieConverter {
<<<<<<< HEAD
     MovieDTO convertToDTO(Movie movie);
     List<MovieDTO> convertToListDTO(List<Movie> movies);
     List<UpcomingMoviesResponse> convertToUpcomingMoviesResponse(List<Movie> movie);
=======
     MovieDto convertToDTO(Movie movie);
     List<MovieDto> convertToListDTO(List<Movie> movies);
>>>>>>> 7b7e16617f13882f2c94c2d7a7e39279891b5ff4
}

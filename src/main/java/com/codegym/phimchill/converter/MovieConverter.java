package com.codegym.phimchill.converter;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface MovieConverter {
     MovieDTO convertToDTO(Movie movie);
     List<MovieDTO> convertToListDTO(List<Movie> movies);
     List<UpcomingMoviesResponse> convertToUpcomingMoviesResponse(List<Movie> movie);
}

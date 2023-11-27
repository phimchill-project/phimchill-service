package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface MovieConverter {

     MovieDto convertToDTO(Movie movie);

     List<MovieDto> convertToListDTO(List<Movie> movies);

}

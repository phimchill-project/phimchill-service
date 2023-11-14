package com.codegym.phimchill.convert;

import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IMovieDTOConvert {
     MovieDTO convertToDTO(Movie movie);
     List<MovieDTO> convertToListDTO(List<Movie> movies);
}

package com.codegym.phimchill.convert;

import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.entity.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDTOConvert {
    public MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        BeanUtils.copyProperties(movie, movieDTO);
        return movieDTO;
    }
    public List<MovieDTO> convertToListDTO(List<Movie> movies){
        List<MovieDTO> listDTO = new ArrayList<>();
        for (Movie movie: movies
             ) {

            listDTO.add(convertToDTO(movie));
        }
        return listDTO;
    }
}

package com.codegym.phimchill.convert.impl;

import com.codegym.phimchill.convert.IMovieDTOConvert;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.entity.Movie;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieConvertImpl implements IMovieDTOConvert {
    @Override
    public MovieDTO convertToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        BeanUtils.copyProperties(movie, movieDTO);
        return movieDTO;
    }

    @Override
    public List<MovieDTO> convertToListDTO(List<Movie> movies) {
        List<MovieDTO> listDTO = new ArrayList<>();
        for (Movie movie: movies
        ) {

            listDTO.add(convertToDTO(movie));
        }
        return listDTO;
    }
}

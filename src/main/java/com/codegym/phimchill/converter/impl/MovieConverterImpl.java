package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.CategoryConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverterImpl implements MovieConverter {
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public MovieDto convertToDTO(Movie movie) {
        MovieDto movieDTO = new MovieDto();
        BeanUtils.copyProperties(movie, movieDTO);
        movieDTO.setCategoryList(categoryConverter.convertToListDto(movie.getCategoryList()));

        return movieDTO;
    }

    @Override
    public List<MovieDto> convertToListDTO(List<Movie> movies) {
        List<MovieDto> listDTO = new ArrayList<>();
        for (Movie movie : movies
        ) {
            listDTO.add(convertToDTO(movie));
        }
        return listDTO;
    }
}

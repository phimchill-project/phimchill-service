package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.convert.IMovieDTOConvert;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.IMovieRepository;
import com.codegym.phimchill.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {
    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private IMovieDTOConvert movieDTOConvert;

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDTO> movieDTOList = movieDTOConvert.convertToListDTO(movieList);
        return movieDTOList;
    }
}

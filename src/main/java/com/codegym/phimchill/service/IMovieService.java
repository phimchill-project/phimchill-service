package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.MovieDTO;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> findAll();
}

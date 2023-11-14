package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAll();
}

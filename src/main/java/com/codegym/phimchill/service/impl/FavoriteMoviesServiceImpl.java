package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.service.FavoriteMoviesService;
import org.springframework.stereotype.Service;

@Service
public class FavoriteMoviesServiceImpl implements FavoriteMoviesService {
    @Override
    public boolean updateFavoriteMovies(long userId, long movieId) {
        return false;
    }
}

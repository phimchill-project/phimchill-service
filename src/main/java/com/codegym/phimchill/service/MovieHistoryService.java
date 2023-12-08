package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MovieHistoryRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieHistoryResponse;
import com.codegym.phimchill.dto.payload.response.MovieHistoryResponse;

public interface MovieHistoryService {

    MovieHistoryResponse save(MovieHistoryRequest movieHistoryRequest) throws Exception;
    ListMovieHistoryResponse getWatchedMovies() throws Exception;
}

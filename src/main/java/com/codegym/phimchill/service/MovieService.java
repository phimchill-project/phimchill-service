package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.MovieHistoryConverter;
import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewFilmRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.NewFilmCategoryDto;
import com.codegym.phimchill.entity.*;
import com.codegym.phimchill.repository.*;
import com.codegym.phimchill.service.CategoryService;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.NameNormalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import java.util.List;
import java.util.Optional;

public interface MovieService {
    ListMovieResponse getUpcomingMovies();

    PagingMovieResponse findAll(int pageNumber, int pageSize);

//    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;

    MovieResponse update(NewMovieRequest updateMovieRequest) throws Exception;

    List<MovieDto> findAll();
    MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception;
    CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest);

    ListMovieResponse getMoviesByCategory(Long id);

    ListMovieResponse getTop10MoviesByViews();

    List<MovieDto> getTop10ByImdb();

    MovieDto findByName(String nameMovie);

    List<Optional<MovieDto>> findMoviesByName(String nameMovive);

    ListMovieResponse getMoviesSortedByIMDBAndDate();

    ListMovieResponse getMoviesbyImbdTop();

    MovieDto getMovieById(Long id);

    ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception;

    ListMovieResponse findMoviesByCategoryId(Long id) throws Exception;

    MovieHistoryResponse DurationByMovieId(Long movieId) throws Exception;

    ListMovieResponse updateMovie(MovieDto movieDto) throws Exception;

    PagingMovieResponse deleteMovies(Long id, Pageable pageable);


}

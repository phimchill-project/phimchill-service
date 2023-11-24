package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.NewMovieCategoryDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.repository.CategoryRepository;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MoviePagingRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.service.CategoryService;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.NameNormalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MoviePagingRepository moviePagingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Qualifier("movieConverterImpl")
    @Autowired
    private MovieConverter movieDtoConvert;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private CategoryRepository categoryRepository;
    private NameNormalizationService nameNormalizationService;
    @Autowired
    private MovieCommentRepository movieCommentRepository;
    @Autowired
    private MovieCommentConverter movieCommentConverter;
    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UpcomingMoviesResponse> getUpcomingMovies() {
        return null;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> movieDTOList = movieDtoConvert.convertToListDTO(movieList);
        return movieDTOList;
    }

    @Override
    public MovieResponse create(NewMovieRequest newTvSeriesRequest) throws Exception {
        Movie newMovie = Movie.builder()
                .name(newTvSeriesRequest.getName())
                .description(newTvSeriesRequest.getDescription())
                .year(newTvSeriesRequest.getYear())
                .duration(newTvSeriesRequest.getDuration())
                .imdb(newTvSeriesRequest.getImdb())
                .image(newTvSeriesRequest.getImage())
                .url(newTvSeriesRequest.getUrl())
                .dateRelease(newTvSeriesRequest.getDateRelease())
                .build();
        Movie savedMovie = movieRepository.save(newMovie);
        List<Category> categoryList = new ArrayList<>();
        for (NewMovieCategoryDto categoryDto : newTvSeriesRequest.getCategoryList()) {
            Category category = categoryService.findById(categoryDto.getId()).orElseThrow(
                    () -> new Exception("Create Movie Fail")
            );
            category.getMovieList().add(savedMovie);
            categoryList.add(category);
        }
        categoryRepository.saveAll(categoryList);
        savedMovie.setCategoryList(categoryList);
        movieRepository.save(savedMovie);
        return MovieResponse.builder()
                .data(movieConverter.convertToDTO(savedMovie))
                .message("Add Movie Success")
                .statusCode(200)
                .build();
    }
    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest) {
        return null;
    }

    @Override
    public List<MovieDto> getTop10ByImdb() {
        return null;
    }

    @Override
    public MovieDto findByName(String nameMovie){
        nameMovie = nameMovie.replaceAll("-", " ");
        List<Movie> movies = movieRepository.findAll();
        Optional<Movie> movie = Optional.empty();
        for (var item : movies) {
            String movieName = nameNormalizationService.normalizeName(item.getName());
            if (movieName.equalsIgnoreCase(nameMovie)) {
                movie = Optional.of(item);
            }
        }
        return movie.map(value -> movieDtoConvert.convertToDTO(value)).orElse(null);
    }

    @Override
    public List<Movie> findByCategoryId(Long id) throws Exception {
        List<Movie> movieList = findByCategoryId(id);
        if(movieList == null){
            throw new Exception("Cannot get movies by category id "+ id);
        }
        return movieList;
    }

    @Override
    public ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception {
        List<MovieComment> movieCommentList = movieCommentRepository.findAllByMovieId(movieId);
        if(movieCommentList == null){
            throw new Exception("Cannot get comments by movie id " + movieId);
        }
        List<MovieCommentDto> movieCommentDtoList = new ArrayList<>();
        for(MovieComment movieComment : movieCommentList){
            movieCommentDtoList.add(movieCommentConverter.convertToDto(movieComment));
        }

        return ListMovieCommentResponse.builder()
                .data(movieCommentDtoList)
                .message("get comments success by movie id " + movieId)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}

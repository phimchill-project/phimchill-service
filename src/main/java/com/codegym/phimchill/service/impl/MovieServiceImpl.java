package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.NewMovieCategoryDto;
import com.codegym.phimchill.dto.payload.response.MovieResponse;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.CategoryRepository;
import com.codegym.phimchill.repository.MoviePagingRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.service.CategoryService;
import com.codegym.phimchill.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Override
    public ListMovieResponse getUpcomingMovies() {
        List<Movie> movies = movieRepository.findUnreleasedMovies();
        ListMovieResponse movieResponse = new ListMovieResponse();
        List<MovieDto> upcomingMoviesDtos = movieConverter.convertToListDTO(movies);
        movieResponse.setData(upcomingMoviesDtos);
        movieResponse.setMessage("Get Upcoming Movies");
        movieResponse.setStatusCode(200);
        return movieResponse ;
    }


    @Override
    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> movieDTOList = movieDtoConvert.convertToListDTO(movieList);
        return movieDTOList;
    }

    @Override
    public MovieResponse create(NewMovieRequest newTvSeriesRequest) {
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
        List<Category> categoryList = new ArrayList<>();
        for (NewMovieCategoryDto categoryDto : newTvSeriesRequest.getCategoryList()) {
            Category category = categoryService.findById(categoryDto.getId()).orElse(null);
            categoryList.add(category);
        }
        newMovie.setCategoryList(categoryList);
        movieRepository.save(newMovie);
        return MovieResponse.builder()
                .data(movieConverter.convertToDTO(newMovie))
                .message("Add Movie Success")
                .statusCode(200)
                .build();
    }
    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest movieNameRequest) {
        return null;
    }

    @Override
    public ListMovieResponse getMoviesByCategory(Long id) {
        List<Movie> movies = movieRepository.findMoviesByCategoryId(id);
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        listMovieResponse.setData(movieConverter.convertToListDTO(movies));
        listMovieResponse.setMessage("Get Blockbuster Moives Success");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse;
    }
    @Override
    public ListMovieResponse getTop10MoviesByViews() {
        Page<Movie> page = movieRepository.findTop10ByOrderByViewsDesc(PageRequest.of(0, 10));
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        List<Movie> movies = page.getContent();
        listMovieResponse.setData(movieConverter.convertToListDTO(movies));
        listMovieResponse.setMessage("Get Top10 Movies By Views");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse;
    }

    public List<MovieDto> getTop10ByImdb() {
        return null;
    }

    @Override
    public MovieDto findByName(String nameMovie){
        nameMovie = nameMovie.replaceAll("-", " ");
        List<Movie> movies = movieRepository.findAll();
        Optional<Movie> movie = Optional.empty();
        for (var item : movies) {
            String movieName = item.getName().replaceAll(":", "").replaceAll("-", " ");
            if (movieName.equalsIgnoreCase(nameMovie)) {
                movie = Optional.of(item);
            }
        }
        return movie.map(value -> movieDtoConvert.convertToDTO(value)).orElse(null);
    }
}

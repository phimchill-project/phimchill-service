package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.MovieHistoryConverter;
import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewFilmRequest;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.dto.NewFilmCategoryDto;
import com.codegym.phimchill.entity.*;
import com.codegym.phimchill.repository.*;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.NameNormalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;
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
    private MovieConverter movieConverter;

    @Autowired
    private NameNormalizationService nameNormalizationService;

    @Autowired
    private MovieCommentRepository movieCommentRepository;

    @Autowired
    private MovieCommentConverter movieCommentConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MovieSubCommentRepository movieSubCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieHistoryRepository movieHistoryRepository;

    @Autowired
    private MovieHistoryConverter movieHistoryConverter;

    @Override
    public ListMovieResponse getUpcomingMovies() {
        List<Movie> movies = movieRepository.findUnreleasedMovies();
        ListMovieResponse movieResponse = new ListMovieResponse();
        List<MovieDto> upcomingMoviesDtos = movieConverter.convertToListDTO(movies);
        movieResponse.setData(upcomingMoviesDtos);
        movieResponse.setMessage("Get Upcoming Movies");
        movieResponse.setStatusCode(200);
        return movieResponse;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> movieDTOList = movieDtoConvert.convertToListDTO(movieList);
        return movieDTOList;
    }

    @Override
    public MovieResponse create(NewFilmRequest newTvSeriesRequest) throws Exception {
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
        for (NewFilmCategoryDto categoryDto : newTvSeriesRequest.getCategoryList()) {
            Category category = categoryRepository.findById(categoryDto.getId()).orElseThrow(
                    () -> new Exception("Create Movie Fail")
            );
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

    public MovieDto getMovieById(Long id) {
        return null;
    }
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
    public MovieDto findByName(String nameMovie) {
        nameMovie = nameNormalizationService.normalizeName(nameMovie);
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
    public List<Optional<MovieDto>> findMoviesByName(String nameMovie) {
        nameMovie = nameNormalizationService.normalizeName(nameMovie);
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> moviesDto = movieConverter.convertToListDTO(movies);
        List<Optional<MovieDto>> moviesDtoNew = new ArrayList<>();
        for (var item : moviesDto) {
            if (moviesDtoNew.size() == 10)
                break;
            String movieName = nameNormalizationService.normalizeName(item.getName());
            if (movieName.contains(nameMovie)) {
                moviesDtoNew.add(Optional.of(item));
            }
        }
        return moviesDtoNew;
    }

    @Override
    public ListMovieResponse getMoviesSortedByIMDBAndDate() {
        List<Movie> moviesData = movieRepository.findReleasedMoviesSortedByIMDBAndDate();
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        listMovieResponse.setData(movieConverter.convertToListDTO(moviesData));
        listMovieResponse.setMessage("get Movies Sorted By IMDB And Date");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse;
    }

    @Override
    public ListMovieResponse getMoviesbyImbdTop() {
        List<Movie> movieList = movieRepository.findFirst10ByOrderByImdbDesc();
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        listMovieResponse.setData(movieConverter.convertToListDTO(movieList));
        listMovieResponse.setMessage("get Movies by Imbd Top");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse;
    }

    @Override
    public ListMovieCommentResponse getMovieCommentsById(Long movieId) throws Exception {
        List<MovieComment> movieCommentList = movieCommentRepository.findAllByMovieId(movieId);
        if(movieCommentList == null) {
            throw new Exception("cannot comments success by movie id " + movieId);
        }
        List<MovieCommentDto> movieCommentDtoList = new ArrayList<>();
        for(MovieComment movieComment : movieCommentList){
            List<MovieSubComment> movieSubCommentList = movieSubCommentRepository.findMovieSubCommentsByMovieComments_Id(movieComment.getId());
            movieComment.setMovieSubCommentsList(movieSubCommentList);
            movieCommentDtoList.add(movieCommentConverter.convertToDto(movieComment));
        }
        return ListMovieCommentResponse.builder()
                .data(movieCommentDtoList)
                .message("get comments success by movie id " + movieId)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public ListMovieResponse findMoviesByCategoryId(Long id) throws Exception {
        List<Movie> movieList = movieRepository.findMoviesByCategoryId(id);
        if(movieList.isEmpty()){
            throw new Exception("Categoty id : " + id + " is not exist");
        }
        List<MovieDto> movieDtoList = movieConverter.convertToListDTO(movieList);
        return ListMovieResponse.builder()
                .data(movieDtoList)
                .message("Get movies by category " + id + " success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public MovieHistoryResponse DurationByMovieId(Long movieId) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new Exception("Cannot find User by emai " + email + "to get duration");
        }
//        Movie movie = movieRepository.findById(movieId).orElseThrow(
//                () -> new Exception("Cannot find movie by id " + movieId + "to save movie history")
//        );
        MovieHistory movieHistory = movieHistoryRepository.findMovieHistoriesByMovie_IdAndAndUser_Id(movieId, user.getId());
        if(movieHistory == null){
            return MovieHistoryResponse.builder()
                    .data(null)
                    .message("Get movie history success")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }
        return MovieHistoryResponse.builder()
                .data(movieHistoryConverter.convertToDto(movieHistory))
                .message("Get movie history success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ListMovieResponse updateMovie(MovieDto movieDto) throws Exception {
        Movie movie = movieRepository.findById(movieDto.getId())
                .orElseThrow(() -> new Exception("Movie not found"));
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setYear(movieDto.getYear());
        movie.setDuration(movieDto.getDuration());
        movie.setImdb(movieDto.getImdb());
        movie.setImage(movieDto.getImage());
        movie.setTrailer(movieDto.getTrailer());
        movie.setUrl(movieDto.getUrl());
        movie.setViews(movieDto.getViews());
        movieRepository.save(movie);
        MovieDto updatedMovieDto = movieConverter.convertToDTO(movie);
        List<MovieDto> updatedMovies = Collections.singletonList(updatedMovieDto);
        return new ListMovieResponse(updatedMovies, "Movie updated successfully", HttpStatus.OK.value());
    }
}
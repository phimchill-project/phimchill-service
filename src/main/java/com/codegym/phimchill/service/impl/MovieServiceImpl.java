package com.codegym.phimchill.service.impl;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.MoviePagingRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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
    public NewMovieResponse create(NewMovieRequest newTvSeriesRequest) {
        return null;
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
            String movieName = item.getName().replaceAll(":", "").replaceAll("-", " ");
            if (movieName.equalsIgnoreCase(nameMovie)) {
                movie = Optional.of(item);
            }
        }
        return movie.map(value -> movieDtoConvert.convertToDTO(value)).orElse(null);
    }
}
package com.codegym.phimchill.service.impl;
import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.UpcomingMovieDto;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.repository.CategoryRepository;
import com.codegym.phimchill.repository.MoviePagingRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.service.CategoryService;
import com.codegym.phimchill.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MoviePagingRepository moviePagingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieDTOConvert;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public UpcomingMoviesResponse getUpcomingMovies() {
        List<Movie> movies = movieRepository.findUnreleasedMovies();
        UpcomingMoviesResponse upcomingMoviesResponse = new UpcomingMoviesResponse();
        List<UpcomingMovieDto> upcomingMoviesDtos = movieDTOConvert.convertToUpcomingMoviesResponse(movies);
        upcomingMoviesResponse.setData(upcomingMoviesDtos);
        upcomingMoviesResponse.setMessage("Get Upcoming Movies");
        upcomingMoviesResponse.setStatusCode(200);
        return upcomingMoviesResponse;
    }


    @Override
    public List<MovieDto> findAll() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDto> movieDTOList = movieDTOConvert.convertToListDTO(movieList);
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
    public ListMovieResponse getMoviesByCategory(Long id) {
        List<Movie> movies = movieRepository.findMoviesByCategoryId(id);
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        listMovieResponse.setData(movieDTOConvert.convertToListDTO(movies));
        listMovieResponse.setMessage("Get Blockbuster Moives Success");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse;
    }
    @Override
    public ListMovieResponse getTop10MoviesByViews() {
        Page<Movie> page = movieRepository.findTop10ByOrderByViewsDesc(PageRequest.of(0, 10));
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        List<Movie> movies = page.getContent();
        listMovieResponse.setData(movieDTOConvert.convertToMovieDTOList(movies));
        listMovieResponse.setMessage("Get Top10 Movies By Views");
        listMovieResponse.setStatusCode(200);
        return listMovieResponse ;
    }
}

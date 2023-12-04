package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieHistoryConverter;
import com.codegym.phimchill.dto.payload.request.MovieHistoryRequest;
import com.codegym.phimchill.dto.payload.response.MovieHistoryResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.MovieHistory;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieHistoryRepository;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MovieHistoryServiceImpl implements MovieHistoryService {
    @Autowired
    private MovieHistoryRepository movieHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieHistoryConverter movieHistoryConverter;

    @Override
    public MovieHistoryResponse save(MovieHistoryRequest movieHistoryRequest) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new Exception("Cannot find User by emai " + email + "to save movie history");
        }
        Movie movie = movieRepository.findById(movieHistoryRequest.getMovieID()).orElseThrow(
                () -> new Exception("Cannot find movie by id " + movieHistoryRequest.getMovieID() + "to save movie history")
        );
        MovieHistory movieHistory = movieHistoryRepository.findMovieHistoriesByMovie_IdAndUser_Id(movieHistoryRequest.getMovieID(), user.getId());
        if(movieHistory == null){
            MovieHistory newMovieHistory = MovieHistory.builder()
                    .user(user)
                    .movie(movie)
                    .duration(movieHistoryRequest.getDuration())
                    .build();
            newMovieHistory = movieHistoryRepository.save(newMovieHistory);
            return MovieHistoryResponse.builder()
                    .data(movieHistoryConverter.convertToDto(newMovieHistory))
                    .message("Save movie history success")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }
        movieHistory.setDuration(movieHistoryRequest.getDuration());
        movieHistory = movieHistoryRepository.save(movieHistory);
        return MovieHistoryResponse.builder()
                .data(movieHistoryConverter.convertToDto(movieHistory))
                .message("Save movie history success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}

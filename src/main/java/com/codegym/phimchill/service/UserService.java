package com.codegym.phimchill.service;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.*;

import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.User;

public interface UserService {
//    String login(LoginRequest loginRequest);
    UserDto login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest) throws Exception;
    boolean isEmailExist(EmailRequest emailRequest);
    User findUserByEmail(String email) throws Exception;
    ListMovieResponse getFavoriteMovies(String email) throws  Exception;
    ListTvSeriesResponse getFavoriTeTVSeries(String email) throws Exception;

    ListMovieResponse deleteFavoriteMovies(String email,Long movieId) throws Exception;
    ListTvSeriesResponse deleteFavoriteTVSeries(String email,Long Id) throws Exception;
    EmailRespone updateEmail(String email, String newEmail ) throws Exception;


    ListMovieResponse editFavoriteMovies(String email, Long movieId) throws Exception;
    EmailRespone updatePass(String email, String pass) throws Exception;

    ListUserResponse findAll(String email) throws Exception;

    boolean checkMember(String email) throws Exception;
}

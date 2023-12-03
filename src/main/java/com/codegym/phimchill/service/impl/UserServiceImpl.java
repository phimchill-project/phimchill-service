package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.converter.impl.TvSeriesConverterImpl;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.Role;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
import com.codegym.phimchill.dto.payload.response.LoginResponse;
import com.codegym.phimchill.dto.payload.response.RegisterResponse;
import com.codegym.phimchill.repository.MovieRepository;
import com.codegym.phimchill.repository.RoleRepository;
import com.codegym.phimchill.repository.TvSeriesRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private TvSeriesConverterImpl tvSeriesConverter;

    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        return user.getName();
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {
        User user = userRepository.findUserByEmail(registerRequest.getEmail());
        if (user == null) {
            String hashPassword = passwordEncoder.encode(registerRequest.getPassword());
            registerRequest.setPassword(hashPassword);
            Role role = roleRepository.findById(2L).orElse(null);
            User user1 = User
//                    .builder()
//                    .email(registerRequest.getEmail())
//                    .password(registerRequest.getPassword())
//                    .name(registerRequest.getName())
//                    .role(role)
//                    .build();
                    .builder()
                    .email(registerRequest.getEmail())
                    .password(registerRequest.getPassword())
                    .name(registerRequest.getName())
                    .movieHistoryList(new ArrayList<>())
                    .tvSeriesFavoriteList(new ArrayList<>())
                    .episodeHistoryList(new ArrayList<>())
                    .movieFavoriteList(new ArrayList<>())
                    .tvSeriesFavoriteList(new ArrayList<>())
                    .commentsMovieList(new ArrayList<>())
                    .movieSubCommentsList(new ArrayList<>())
                    .listCommentTaggedIn(new ArrayList<>())
                    .listSubCommnetTaggedIn(new ArrayList<>())
                    .role(role)
                    .build();
            userRepository.save(user1);
            RegisterDto registerDto = userConverter.converterRegister(user1);
            RegisterResponse registerResponse = new RegisterResponse();
            registerResponse.setData(registerDto);
            registerResponse.setStatusCode(200);
            registerResponse.setMessage("Register Success");
            return registerResponse;
        } else {
            throw new Exception();
        }
    }

    @Override
    public boolean isEmailExist(EmailRequest email) {
        User user = userRepository.findUserByEmail(email.getEmail());
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("Email cannot find");
        }
        return user;
    }

    @Override
    public boolean updateEmail(String newEmail) {
        User existingUser = userRepository.findUserByEmail(newEmail);
        if (existingUser != null) {
            existingUser.setEmail(newEmail);
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    @Override
    public ListMovieResponse getFavoriteMovies(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("Cannot get list movie favorite");
        }
        ListMovieResponse listMovieResponse = new ListMovieResponse();
        List<MovieDto> movieDtoList = movieConverter.convertToListDTO(user.getMovieFavoriteList());
        listMovieResponse.setData(movieDtoList);
        listMovieResponse.setMessage("Get Movie Success");
        listMovieResponse.setStatusCode(HttpStatus.OK.value());
        return listMovieResponse;
    }

    @Override
    public ListTvSeriesResponse getFavoriTeTVSeries(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("Cannot get list  TvSeries favorite");
        }
        ListTvSeriesResponse listTvSeriesResponse = new ListTvSeriesResponse();
        List<TvSeriesDto> tvSeriesDtos = tvSeriesConverter.convertToListDTO(user.getTvSeriesFavoriteList());
        listTvSeriesResponse.setData(tvSeriesDtos);
        listTvSeriesResponse.setMessage("Get list  TvSeries favorite");
        listTvSeriesResponse.setStatusCode(HttpStatus.OK.value());
        return listTvSeriesResponse;
    }

    @Override
    public ListMovieResponse deleteFavoriteMovies(String email, Long movieId) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new Exception("Movie not found"));
        user.getMovieFavoriteList().remove(movie);
        movie.getUserFavoriteList().remove(user);
        movieRepository.save(movie);
        userRepository.save(user);
        return new ListMovieResponse(new ArrayList<>(), "Movie removed from favorites", HttpStatus.OK.value());
    }
    @Override
    public ListTvSeriesResponse deleteFavoriteTVSeries(String email,Long Id) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        TVSeries tvSeriesDto = tvSeriesRepository.findById(Id)
                .orElseThrow(() -> new Exception("TV Series not found"));
        user.getTvSeriesFavoriteList().remove(tvSeriesDto);
        user.getTvSeriesFavoriteList().remove(tvSeriesDto);
        userRepository.save(user);
        return new ListTvSeriesResponse(
                new ArrayList<>(),
                "TV Series removed from favorites",
                HttpStatus.OK.value()
        );
    }

}


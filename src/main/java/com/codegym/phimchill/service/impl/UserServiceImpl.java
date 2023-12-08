package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.converter.impl.TvSeriesConverterImpl;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.dto.payload.request.EmailRequest;
import com.codegym.phimchill.dto.payload.request.RegisterRequest;
import com.codegym.phimchill.dto.payload.response.*;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.Role;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.dto.payload.request.LoginRequest;
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
import java.util.Optional;
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
    public UserDto login(LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        return userConverter.converterToDTO(user);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws Exception {
        User user = userRepository.findUserByEmail(registerRequest.getEmail());
        if (user == null) {
            String hashPassword = passwordEncoder.encode(registerRequest.getPassword());
            registerRequest.setPassword(hashPassword);
            Role role = roleRepository.findById(2L).orElse(null);
            User user1 = User
                    .builder()
                    .email(registerRequest.getEmail())
                    .password(registerRequest.getPassword())
                    .name(registerRequest.getName())
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
    public EmailRespone updateEmail(String email, String newEmail) throws Exception {
        User existingUser = userRepository.findUserByEmail(newEmail);
        if (existingUser != null) {
            throw new Exception("User not found");
        }
        User newUser = userRepository.findUserByEmail(email);
        newUser.setEmail(newEmail);
        userRepository.save(newUser);
        return new EmailRespone(
                new ArrayList<>(),
                "Update Email ok",
                HttpStatus.OK.value()
        );
    }
    @Override
    public EmailRespone updatePass(String email, String pass) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        String hashPassword = passwordEncoder.encode(pass);
        user.setPassword(hashPassword);
        userRepository.save(user);
        return new EmailRespone(
                new ArrayList<>(),
                "Update Email ok",
                HttpStatus.OK.value()
        );
    }

    @Override
    public ListMovieResponse editFavoriteMovies(String email, Long movieId) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new Exception("Movie not found"));
        user.getMovieFavoriteList().add(movie);
        movie.getUserFavoriteList().add(user);
        movieRepository.save(movie);
        userRepository.save(user);
        return new ListMovieResponse(new ArrayList<>(), "Movie removed from favorites", HttpStatus.OK.value());
    }

    @Override
    public ListUserResponse findAll(String email) throws Exception {
        User currentUser = userRepository.findUserByEmail(email);
        if(currentUser == null) {
            throw new Exception("email not exist");
        }
        List<User> userLists = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userLists){
            if(user.getId() == currentUser.getId()){
                continue;
            }
            userDtoList.add(userConverter.converterToDTO(user));
        }
        return ListUserResponse.builder()
                .data(userDtoList)
                .message("Get all user success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public boolean checkMember(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            throw new Exception("email not exist");
        }
        return user.isMember();
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
    public ListTvSeriesResponse deleteFavoriteTVSeries(String email, Long Id) throws Exception {
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
package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.UpcomingMovieDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpcomingMoviesResponse {
    private List<UpcomingMovieDto> data;
    private String message;
    private int statusCode;
}

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
<<<<<<< HEAD
    private List<UpcomingMovieDto> data;
    private String message;
    private int statusCode;
=======
    private Long id;

    private String name;

    private String description;

    private Date year;

    private float imdb;

    private String image;

    private String trailer;

    private boolean isRelease;
>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
}

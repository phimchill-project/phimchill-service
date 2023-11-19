package com.codegym.phimchill.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpcomingMoviesResponse {
    private Long id;
    private String name;
    private String description;
    private Date year;
    private float imdb;
    private String image;
    private String trailer;
    private boolean isRelease;
}

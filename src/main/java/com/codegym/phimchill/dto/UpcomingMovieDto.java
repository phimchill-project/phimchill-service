package com.codegym.phimchill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpcomingMovieDto {
    private Long id;
    private String name;
    private String description;
    private Date year;
    private int duration;
    private float imdb;
    private String image;
    private String trailer;
    private String url;
    private Integer views;
    private List<CategoryDto> categoryList;
}
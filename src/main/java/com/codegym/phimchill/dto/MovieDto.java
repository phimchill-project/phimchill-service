package com.codegym.phimchill.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;

    private String name;

    private String description;

    private Date year;

    private int duration;

    private float imdb;

    private String image;

    private String trailer;

    private String url;
<<<<<<< HEAD
    private Integer views;
=======

>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
    private List<CategoryDto> categoryList;
}

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

    private int year;

    private int duration;

    private float imdb;

    private String image;

    private String trailer;

    private String url;

    private Integer views;

    private List<CategoryDto> categoryList;

    private List<Long> userIdListLiked;

    private Date dateRelease;
}

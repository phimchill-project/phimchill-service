package com.codegym.phimchill.dto.payload.request;

import com.codegym.phimchill.dto.CategoryDto;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewMovieRequest {
    private Long id;
    private String name;

    private String description;

    private int duration;

    private int year;

    private float imdb;

    private String image;

    private String url;

    private Date dateRelease;

    private List<CategoryDto> categoryList;
}


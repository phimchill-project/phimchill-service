package com.codegym.phimchill.dto.payload.request;

import com.codegym.phimchill.dto.NewFilmCategoryDto;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewFilmRequest {
    private String name;

    private String description;

    private int duration;

    private int year;

    private float imdb;

    private String image;

    private String url;

    private Date dateRelease;

    private List<NewFilmCategoryDto> categoryList;
}
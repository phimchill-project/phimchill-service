package com.codegym.phimchill.dto.payload.request;

import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.NewMovieCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private List<NewMovieCategoryDto> categoryList;
}

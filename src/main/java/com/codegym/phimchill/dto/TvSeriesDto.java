package com.codegym.phimchill.dto;

import lombok.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TvSeriesDto {
    private Long id;

    private String name;

    private String description;

    private int year;

    private float imdb;

    private String image;

    private Date dateRelease;

    private List<CategoryDto> categoryList;

    private List<SeasonDto> seasonList;
    private Boolean isDelete;
}

package com.codegym.phimchill.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieHistoryWithMovieDetailDto {
    private String movieName;
    private String movieImg;
    private float duration;
}

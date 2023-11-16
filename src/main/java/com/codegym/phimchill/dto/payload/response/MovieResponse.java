package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponse {
    private MovieDto data;
    private String message;
    private int statusCode;
}

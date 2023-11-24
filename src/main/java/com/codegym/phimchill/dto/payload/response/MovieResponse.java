package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieResponse {
    private MovieDto data;
    private String message;
    private int statusCode;
}

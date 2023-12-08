package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.PagingMovieResponseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagingMovieResponse {
    private PagingMovieResponseDto data;
    private String message;
    private int statusCode;
}

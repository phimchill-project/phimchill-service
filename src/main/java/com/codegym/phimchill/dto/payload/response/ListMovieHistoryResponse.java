package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieHistoryWithMovieDetailDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListMovieHistoryResponse {
    private List<MovieHistoryWithMovieDetailDto> data;
    private String message;
    private int statusCode;
}

package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieSubCommentDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListMovieSubCommentResponse {
    private List<MovieSubCommentDto> data;
    private String message;
    private int statusCode;
}

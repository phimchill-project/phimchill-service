package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieCommentDto;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListMovieCommentResponse {
    private List<MovieCommentDto> data;
    private String message;
    private int statusCode;
}

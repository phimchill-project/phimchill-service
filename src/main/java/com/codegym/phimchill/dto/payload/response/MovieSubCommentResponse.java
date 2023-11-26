package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieSubCommentDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieSubCommentResponse {
    private MovieSubCommentDto data;
    private String message;
    private int statusCode;
}

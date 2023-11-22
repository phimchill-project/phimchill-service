package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieCommentDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentResponse {
    private MovieCommentDto data;
    private String message;
    private int statusCode;
}

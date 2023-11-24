package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.RepliedMovieCommentDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RepliedMovieCommentResponse {
    private RepliedMovieCommentDto data;
    private String message;
    private int statusCode;
}

package com.codegym.phimchill.dto.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TotalLikesMovieCommentResponse {
    private Integer data;
    private String message;
    private int statusCode;
}

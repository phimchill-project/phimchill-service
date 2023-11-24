package com.codegym.phimchill.dto.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckMovieNameExistResponse {
    boolean data;

    int statusCode;

    String message;
}

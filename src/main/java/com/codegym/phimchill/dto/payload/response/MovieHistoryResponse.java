package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieHistoryDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieHistoryResponse {
    private MovieHistoryDto data;
    private String message;
    private int statusCode;
}

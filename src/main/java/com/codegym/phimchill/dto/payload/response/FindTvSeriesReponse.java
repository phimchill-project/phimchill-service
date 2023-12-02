package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindTvSeriesReponse {
    TvSeriesDto data;

    private int statusCode;

    private String message;
}

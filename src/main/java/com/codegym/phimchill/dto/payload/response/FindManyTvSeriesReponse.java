package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindManyTvSeriesReponse {
    List<Optional<TvSeriesDto>> data;

    private int statusCode;

    private String message;
}

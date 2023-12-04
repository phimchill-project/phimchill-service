package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.TvSeriesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvSeriesResponse {
    private List<TvSeriesDto> listTVSeries;
    private String title;
    private int statusCode;
}

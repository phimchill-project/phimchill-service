package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.TvSeriesDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListTvSeriesResponse {
    private List<TvSeriesDto> data;
    private String message;
    private int statusCode;
}

package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.TVSeriesHistoryWithDetailDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListTVSeriesHistoryResponse {
    private List<TVSeriesHistoryWithDetailDto> data;
    private String message;
    private int statusCode;
}

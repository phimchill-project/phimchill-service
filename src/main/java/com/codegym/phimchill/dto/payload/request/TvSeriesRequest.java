package com.codegym.phimchill.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TvSeriesRequest {
    private Long user_id;
    private Long tvSeries_id;
}

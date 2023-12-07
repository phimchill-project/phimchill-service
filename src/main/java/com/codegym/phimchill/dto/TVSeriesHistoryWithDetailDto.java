package com.codegym.phimchill.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TVSeriesHistoryWithDetailDto {
    private String tvseriesName;
    private String tvseriesImg;
    private float duration;
}

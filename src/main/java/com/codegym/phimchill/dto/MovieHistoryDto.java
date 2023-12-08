package com.codegym.phimchill.dto;

import lombok.*;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieHistoryDto {
    private Long id;
    private float duration;
}

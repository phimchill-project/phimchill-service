package com.codegym.phimchill.dto.payload.request;

import lombok.*;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieHistoryRequest {
    private Long movieID;
    private float duration;
}

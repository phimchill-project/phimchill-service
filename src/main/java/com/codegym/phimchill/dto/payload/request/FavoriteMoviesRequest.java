package com.codegym.phimchill.dto.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoriteMoviesRequest {
    private Long userId;
    private Long MovieIds;

}

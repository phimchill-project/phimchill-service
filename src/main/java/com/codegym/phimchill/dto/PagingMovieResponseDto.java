package com.codegym.phimchill.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagingMovieResponseDto {
    private int pageNumber;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<MovieDto> data;
}

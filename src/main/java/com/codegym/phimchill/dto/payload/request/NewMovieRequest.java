package com.codegym.phimchill.dto.payload.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewMovieRequest {
    private String name;
    private String description;
    private int year;
    private float imdb;
}

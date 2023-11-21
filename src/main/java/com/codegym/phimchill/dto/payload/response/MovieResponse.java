package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.User;
import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieResponse {
    private MovieDto data;
    private String message;
    private int statusCode;
}

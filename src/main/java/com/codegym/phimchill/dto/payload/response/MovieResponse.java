package com.codegym.phimchill.dto.payload.response;
<<<<<<< HEAD
=======

>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
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

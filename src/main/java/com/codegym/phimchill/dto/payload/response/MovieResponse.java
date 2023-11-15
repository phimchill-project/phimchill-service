package com.codegym.phimchill.dto.payload.response;
import com.codegym.phimchill.dto.MovieDTO;
import com.codegym.phimchill.dto.RegisterDto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieResponse {
    private MovieDTO data;
    private String message;
    private int statusCode;
}

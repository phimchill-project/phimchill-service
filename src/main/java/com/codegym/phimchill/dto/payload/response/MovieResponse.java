package com.codegym.phimchill.dto.payload.response;
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
    private Long id;
    private String name;
    private String description;
    private Date year;
    private int duration;
    private float imdb;
    private String image;
    private String trailer;
    private RegisterDto data;
    private String message;
    private int statusCode;
    private List<Category> categoryList;
    private List<User> userHistoryList;
    private List<User> userFavoriteList;
}

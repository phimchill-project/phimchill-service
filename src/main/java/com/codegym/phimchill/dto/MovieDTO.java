package com.codegym.phimchill.dto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String name;
    private String description;
    private Date year;
    private int duration;
    private float imdb;
    private String image;
    private String trailer;
    private List<Category> categoryList;
    private List<User> userHistoryList;
    private List<User> userFavoriteList;
}

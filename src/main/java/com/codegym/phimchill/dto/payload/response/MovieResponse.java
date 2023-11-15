package com.codegym.phimchill.dto.payload.response;
<<<<<<< HEAD
=======

import com.codegym.phimchill.dto.RegisterDto;
>>>>>>> 8afa229d6c79178d59d395e4b061a2bf4afff784
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.User;
import java.sql.Date;
import java.util.List;

public class MovieResponse {
<<<<<<< HEAD
    private Long id;
    private String name;
    private String description;
    private Date year;
    private int duration;
    private float imdb;
    private String image;
    private String trailer;
=======
    private RegisterDto data;
    private String message;
    private int statusCode;

>>>>>>> 8afa229d6c79178d59d395e4b061a2bf4afff784
    private List<Category> categoryList;
    private List<User> userHistoryList;
    private List<User> userFavoriteList;
}

package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "MOVIE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "YEAR")
    private int year; // năm phát hành
    @Column(name = "DURATION")
    private int duration; //thời lượnng tính theo phút
    @Column(name = "IMDB")
    private float imdb; // điểm đánh giá
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "TRAILER")
    private String trailer;
    @Column (name = "URL")
    private String url;
    @Column(name = "IS_RELEASE")
    private boolean isRelease;
    @Column (name = "DATE_RELEASE")
    private Date dateRelease;
    @Column (name = "VIEWS")
    private Integer views;
    @ManyToMany(mappedBy = "movieList")
    private List<Category> categoryList;
    @ManyToMany(mappedBy = "movieHistoryList")
    private List<User> userHistoryList;
    @ManyToMany(mappedBy = "movieFavoriteList")
    private List<User> userFavoriteList;
}

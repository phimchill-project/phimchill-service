package com.codegym.phimchill.entity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "TVSERIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TVSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "YEAR")
    private Date year; // năm phát hành
    @Column(name = "IMDB")
    private float imdb; // điểm đánh giá
    @Column(name = "IMAGE")
    private String image;
    @ManyToMany(mappedBy = "tvSeriesList")
    private List<Category> categoryList;
    @OneToMany(mappedBy = "tvSeries")
    private List<Season> seasonList;
    @ManyToMany(mappedBy = "tvSeriesHistoryList")
    private List<User> userHistoryList;
    @ManyToMany(mappedBy = "tvSeriesFavoriteList")
    private List<User> userFavoriteList;
}
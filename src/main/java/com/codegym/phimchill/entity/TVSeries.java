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
    private int year; // năm phát hành

    @Column(name = "IMDB")
    private Float imdb; // điểm đánh giá

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "IS_RELEASE")
    private boolean isRelease;

    @Column (name = "DATE_RELEASE")
    private Date dateRelease;

    @Column (name = "VIEWS")
    private Integer views;

    @ManyToMany(mappedBy = "tvSeriesList")
    @EqualsAndHashCode.Exclude
    private List<Category> categoryList;

    @OneToMany(mappedBy = "tvSeries")
    private List<Season> seasonList;

    @ManyToMany(mappedBy = "tvSeriesFavoriteList")
    private List<User> userFavoriteList;

    @Column(name = "IS_DELETE")
    private Boolean isDelete = false;


}

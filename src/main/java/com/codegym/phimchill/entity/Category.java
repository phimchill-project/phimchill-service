package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "CATEGORY_MOVIE",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private List<Movie> movieList;
    @ManyToMany
    @JoinTable(
            name = "category_tvseries",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TVSERIES_ID"))
    private List<TVSeries> tvSeriesList;
}

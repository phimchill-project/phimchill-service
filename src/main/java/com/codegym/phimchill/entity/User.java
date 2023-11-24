package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;
    @ManyToMany
    @JoinTable(
            name = "HISTORY_MOVIE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private List<Movie> movieHistoryList;
    @ManyToMany
    @JoinTable(
            name = "HISTORY_TVSERIES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TVSERIES_ID"))
    private List<TVSeries> tvSeriesHistoryList;
    @ManyToMany
    @JoinTable(
            name = "HISTORY_EPISODE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "EPISODE_ID"))
    private List<Episode> episodeHistoryList;
    @ManyToMany
    @JoinTable(
            name = "FAVORITE_MOVIE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MOVIE_ID"))
    private List<Movie> movieFavoriteList;
    @ManyToMany
    @JoinTable(
            name = "FAVORITE_TVSERIES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TVSERIES_ID"))
    private List<TVSeries> tvSeriesFavoriteList;
    @OneToMany(mappedBy = "user")
    private List<MovieComment> commentsMovieList;
    @OneToMany(mappedBy = "user")
    private List<RepliedMovieComment> repliedMovieCommentsList;
}

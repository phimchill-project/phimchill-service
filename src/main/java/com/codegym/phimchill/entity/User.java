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
    @OneToMany(mappedBy = "user")
    private List<MovieHistory> movieHistoryList;
    @OneToMany(mappedBy = "user")
    private List<EpisodeHistory> episodeHistoryList;
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
    @OneToMany(mappedBy = "userComment")
    private List<MovieComment> commentsMovieList;
    @OneToMany(mappedBy = "subCommentUser")
    private List<MovieSubComment> movieSubCommentsList;
    @ManyToMany(mappedBy = "listUserTaggedInComment")
    private List<MovieComment> listCommentTaggedIn;
    @ManyToMany(mappedBy = "listUserTaggedInSubComment")
    private List<MovieSubComment> listSubCommentTaggedIn;
    @ManyToMany(mappedBy = "userListLikeComment")
    private List<MovieComment> movieCommentListUserLiked;
    @ManyToMany(mappedBy = "userListLikeSubComment")
    private List<MovieSubComment> movieSubCommentListUserLiked;
    @OneToMany(mappedBy = "user")
    private List<Payment> paymentList;
    @Column(name = "IS_MEMBER")
    private boolean isMember;
    @ManyToMany(mappedBy = "usersInChat", fetch = FetchType.EAGER)
    private List<Chat> chatList;
    @OneToMany(mappedBy = "userSend")
    private List<Message> messageList;
}

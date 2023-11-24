package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "MOVIE_COMMENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DATE_POST")
    private Date datePost;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    @OneToMany(mappedBy = "movieComments")
    private List<RepliedMovieComment> repliedMovieCommentsList;
}

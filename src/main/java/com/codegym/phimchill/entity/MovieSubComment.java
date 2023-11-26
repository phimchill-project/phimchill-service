package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "MOVIE_SUB_COMMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieSubComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "DATE_POST")
    private Date datePost;
    @ManyToOne
    @JoinColumn(name = "MOVIE_COMMENTS_ID", referencedColumnName = "ID")
    private MovieComment movieComments;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User subCommentUser;
    @ManyToMany
    @JoinTable(
            name = "USER_TAGGED_IN_SUB_COMMENT",
            joinColumns = @JoinColumn(name = "SUB_COMMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_TAGGED_IN_SUB_COMMENT", referencedColumnName = "ID")
    )
    private List<User> listUserTaggedInSubComment;
}

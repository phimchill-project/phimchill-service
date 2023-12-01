package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

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
    @Column(name = "TOTAL_LIKES")
    private int totalLike;
    @ManyToMany
    @JoinTable(
            name = "USER_LIKE_MOVIE_SUB_COMMENT",
            joinColumns = @JoinColumn(name = "MOVIE_SUB_COMMENT_ID"),
            inverseJoinColumns = @JoinColumn (name = "USER_ID")
    )
    private List<User> userListLikeSubComment;
}

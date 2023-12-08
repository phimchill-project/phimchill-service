package com.codegym.phimchill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp datePost;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userComment;
    @OneToMany(mappedBy = "movieComments")
    private List<MovieSubComment> movieSubCommentsList;
    @ManyToMany
    @JoinTable(
            name = "USER_TAGGED_IN_COMMENT",
            joinColumns = @JoinColumn(name = "COMMENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID_TAGGED", referencedColumnName = "ID")
    )
    private List<User> listUserTaggedInComment;
    @Column(name = "TOTAL_LIKES")
    private int totalLike;
    @ManyToMany
    @JoinTable(
            name = "USER_LIKE_MOVIE_COMMENT",
            joinColumns = @JoinColumn(name = "MOVIE_COMMENT_ID"),
            inverseJoinColumns = @JoinColumn (name = "USER_ID")
    )
    private List<User> userListLikeComment;
}


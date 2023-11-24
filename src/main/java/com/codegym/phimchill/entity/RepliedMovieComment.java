package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REPLIED_COMMENTS_MOVIE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RepliedMovieComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "COMMENT")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "MOVIECOMMENTS_ID", referencedColumnName = "ID")
    private MovieComment movieComments;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "REPLIED_TO_USER_ID", referencedColumnName = "ID")
    private User repliedToUser;
}

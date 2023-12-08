
package com.codegym.phimchill.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "HISTORY_MOVIE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieHistory {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    @Column(name = "DURATION")
    private float duration;
}

package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@Table(name = "HISTORY_EPISODE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpisodeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EPISODE_ID", referencedColumnName = "ID")
    private Episode episode;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    @Column(name = "DURATION")
    private Duration duration;
}

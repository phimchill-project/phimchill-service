package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HISTORY_TVSERIES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TVSeriesHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TVSERIES_ID", referencedColumnName = "ID")
    private TVSeries tvSeries;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
    @Column(name = "DURATION")
    private float duration;
}

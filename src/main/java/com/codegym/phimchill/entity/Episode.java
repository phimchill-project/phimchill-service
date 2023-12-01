package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "EPISODE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "IS_RELEASE")
    private boolean isRelease;
    @Column (name = "DATE_RELEASE")
    private Date dateRelease;
    @Column (name = "URL")
    private String url;
    @ManyToOne
    @JoinColumn(name = "SEASON_ID", referencedColumnName = "ID")
    private Season season;
    @OneToMany(mappedBy = "episode")
    private List<EpisodeHistory> episodeHistoryList;
}

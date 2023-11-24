package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "SEASON")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_RELEASE")
    private boolean isRelease;

    @Column (name = "DATE_RELEASE")
    private Date dateRelease;

    @ManyToOne
    @JoinColumn(name = "TVSERIES_ID", referencedColumnName = "ID")
    private TVSeries tvSeries;

    @OneToMany(mappedBy = "season")
    private List<Episode> episodeList;
}

package com.codegym.phimchill.entity;
import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "VIDEO")
    private String video;
    @ManyToOne
    @JoinColumn(name = "SEASON_ID", referencedColumnName = "ID")
    private Season season;
    @ManyToMany(mappedBy = "episodeHistoryList")
    private List<User> userEpisodeList;
}

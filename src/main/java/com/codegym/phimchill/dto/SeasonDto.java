package com.codegym.phimchill.dto;

import lombok.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SeasonDto {
    private Long id;

    private String name;

    private Date dateRelease;

    private List<EpisodeDto> episodeList;
}

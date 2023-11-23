package com.codegym.phimchill.dto;

import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EpisodeDto {
    private Long id;

    private String name;

    private String video;

    private Date dateRelease;

    private String url;
}

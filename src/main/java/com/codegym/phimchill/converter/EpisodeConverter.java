package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.EpisodeDto;
import com.codegym.phimchill.dto.SeasonDto;
import com.codegym.phimchill.entity.Episode;
import com.codegym.phimchill.entity.Season;

public interface EpisodeConverter {
    EpisodeDto convertToDto(Episode episode);
}

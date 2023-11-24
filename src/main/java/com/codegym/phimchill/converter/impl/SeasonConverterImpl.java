package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.SeasonConverter;
import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.EpisodeDto;
import com.codegym.phimchill.dto.SeasonDto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Season;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeasonConverterImpl implements SeasonConverter {
    @Autowired
    private EpisodeConverterImpl episodeConverter;

    @Override
    public SeasonDto convertToDto(Season element) {
        SeasonDto result = new SeasonDto();
        BeanUtils.copyProperties(element, result);

        List<EpisodeDto> episodeDtoList = element.getEpisodeList().stream()
                .map(season -> episodeConverter.convertToDto(season))
                .collect(Collectors.toList());
        result.setEpisodeList(episodeDtoList);

        return result;
    }
}

package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.EpisodeConverter;
import com.codegym.phimchill.dto.EpisodeDto;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Episode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EpisodeConverterImpl implements EpisodeConverter {
    @Override
    public EpisodeDto convertToDto(Episode element) {
        EpisodeDto result = new EpisodeDto();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.CategoryConverter;
import com.codegym.phimchill.converter.SeasonConverter;
import com.codegym.phimchill.converter.TvSeriesConverter;
import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.SeasonDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.entity.TVSeries;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TvSeriesConverterImpl implements TvSeriesConverter {
    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private SeasonConverter seasonConverter;

    @Override
    public TvSeriesDto convertToDto(TVSeries element) {
        TvSeriesDto result = new TvSeriesDto();
        BeanUtils.copyProperties(element, result);

        List<CategoryDto> categoryDtoList = element.getCategoryList().stream()
                .map(category -> categoryConverter.convertToDto(category))
                .collect(Collectors.toList());
        result.setCategoryList(categoryDtoList);

        List<SeasonDto> seasonDtoList = element.getSeasonList().stream()
                .map(season -> seasonConverter.convertToDto(season))
                .collect(Collectors.toList());
        result.setSeasonList(seasonDtoList);

        return result;
    }

    @Override
    public List<TvSeriesDto> convertToListDTO(List<TVSeries> elements) {
        return  elements.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

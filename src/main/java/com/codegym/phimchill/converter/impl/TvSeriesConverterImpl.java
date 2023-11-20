package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.TvSeriesConverter;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.entity.TVSeries;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TvSeriesConverterImpl implements TvSeriesConverter {
    @Override
    public TvSeriesDto convertToDTO(TVSeries element) {
        TvSeriesDto result= new TvSeriesDto();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public List<TvSeriesDto> convertToListDTO(List<TVSeries> elements) {
        return  elements.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

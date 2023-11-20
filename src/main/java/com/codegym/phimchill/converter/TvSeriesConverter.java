package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.TVSeries;

import java.util.List;

public interface TvSeriesConverter {
    TvSeriesDto convertToDTO(TVSeries tvSeries);
    List<TvSeriesDto> convertToListDTO(List<TVSeries> tvSeriesList);
}

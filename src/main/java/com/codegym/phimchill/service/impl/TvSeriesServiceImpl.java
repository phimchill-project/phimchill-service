package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.TvSeriesConverter;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.repository.TvSeriesPagingRepository;
import com.codegym.phimchill.repository.TvSeriesRepository;
import com.codegym.phimchill.service.NameNormalizationService;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class TvSeriesServiceImpl implements TvSeriesService {
    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @Autowired
    private TvSeriesPagingRepository tvSeriesPagingRepository;

    @Autowired
    private TvSeriesConverter tvSeriesConverter;

    @Autowired
    private NameNormalizationService nameNormalizationService;

    @Override
    public NewMovieResponse create(NewMovieRequest newTvSeriesRequest) {

        return null;
    }

    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest tvSeriesNameRequest) {
        Optional<TVSeries> tvSeries = Optional.ofNullable(tvSeriesRepository.findByName(tvSeriesNameRequest.getName()));
        if(tvSeries.isEmpty()){
            return CheckMovieNameExistResponse.builder()
                    .data(true)
                    .statusCode(200)
                    .message("TvSeries is not exist")
                    .build();
        }
        return  CheckMovieNameExistResponse.builder()
                .data(false)
                .statusCode(302 )
                .message("TvSeries is not exist")
                .build();
    }

    @Override
    public List<TvSeriesDto> getTop10ByImdb() {
        return tvSeriesConverter.convertToListDTO(tvSeriesRepository.findFirst10ByOrderByImdbDesc());
    }

    @Override
    public List<TvSeriesDto> getTop10Newest() {
        return tvSeriesConverter.convertToListDTO(tvSeriesRepository.findFirst10ByOrderByDateReleaseDesc());
    }

    @Override
    public TvSeriesDto findByName(String nameTvSeries){
        nameTvSeries = nameTvSeries.replaceAll("-", " ");
        List<TVSeries> seriesList = tvSeriesRepository.findAll();
        Optional<TVSeries> series = Optional.empty();
        for (var item : seriesList) {
            String tvSeriesName = nameNormalizationService.normalizeName(item.getName());
            if (tvSeriesName.equalsIgnoreCase(nameTvSeries)) {
                series = Optional.of(item);
            }
        }
        return series.map(value -> tvSeriesConverter.convertToDto(value)).orElse(null);
    }
}

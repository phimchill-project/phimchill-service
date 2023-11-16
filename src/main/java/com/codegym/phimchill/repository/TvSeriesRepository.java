package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeries;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TvSeriesRepository extends PagingAndSortingRepository<TVSeries, Long> {
    TVSeries findByName(String name);
}

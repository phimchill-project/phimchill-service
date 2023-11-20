package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeries;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TvSeriesRepository extends PagingAndSortingRepository<TVSeries, Long> {
    TVSeries findByName(String name);
    List<TVSeries> findFirst10ByOrderByImdbDesc();
    List<TVSeries> findFirst10ByOrderByDateReleaseDesc();
}
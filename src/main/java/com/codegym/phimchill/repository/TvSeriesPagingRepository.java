package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeries;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TvSeriesPagingRepository extends PagingAndSortingRepository<TVSeries, Long> {
}

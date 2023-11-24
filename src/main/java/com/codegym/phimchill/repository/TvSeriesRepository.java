package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvSeriesRepository extends JpaRepository<TVSeries, Long> {
    TVSeries findByName(String name);

    List<TVSeries> findFirst10ByOrderByImdbDesc();

    List<TVSeries> findFirst10ByOrderByDateReleaseDesc();
}

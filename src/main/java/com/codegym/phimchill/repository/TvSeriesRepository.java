package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TvSeriesRepository extends JpaRepository<TVSeries, Long> {
    TVSeries findByName(String name);

    List<TVSeries> findFirst10ByIsDeleteFalseOrderByImdbDesc();

    List<TVSeries> findFirst10ByIsDeleteFalseOrderByDateReleaseDesc();

    @Query("SELECT m FROM TVSeries m JOIN m.categoryList c WHERE c.id = :id")
    List<TVSeries> findAllByCategoryId (long id);
    Optional<TVSeries> findById(Long Id);
}

package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.TVSeriesHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TVSeriesHistoryRepository extends JpaRepository<TVSeriesHistory, Long> {
    List<TVSeriesHistory> findTVSeriesHistoriesByUser_Id(Long id);
}

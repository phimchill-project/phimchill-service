
package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.MovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieHistoryRepository extends JpaRepository<MovieHistory, Long> {

    MovieHistory findMovieHistoriesByMovie_IdAndUser_Id(Long movieId, Long UserId);

    List<MovieHistory> findMovieHistoriesByUser_Id(Long id);
}


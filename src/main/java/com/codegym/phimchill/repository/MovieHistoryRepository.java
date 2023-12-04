
package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.MovieHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieHistoryRepository extends JpaRepository<MovieHistory, Long> {
    MovieHistory findMovieHistoriesByMovie_IdAndAndUser_Id(Long movieId, Long UserId);
}


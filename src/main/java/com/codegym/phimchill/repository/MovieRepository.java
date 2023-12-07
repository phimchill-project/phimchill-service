package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.isRelease = false")
    List<Movie> findUnreleasedMovies();

    @Query("SELECT movie FROM Movie movie JOIN movie.categoryList c WHERE c.id = :categoryId")
    List<Movie> findMoviesByCategoryId(Long categoryId);
    @Query("SELECT m FROM Movie m ORDER BY m.views DESC")
    Page<Movie> findTop10ByOrderByViewsDesc(Pageable pageable);

    List<Movie> findFirst10ByOrderByImdbDesc();
    @Query(value = "SELECT * FROM movie WHERE is_release = 1 ORDER BY imdb DESC, date_release DESC", nativeQuery = true)
    List<Movie> findReleasedMoviesSortedByIMDBAndDate();

    @Query("SELECT m FROM Movie m WHERE m.dateRelease > CURRENT_DATE ORDER BY m.imdb DESC")
    List<Movie> findUpcomingMoviesOrderByImdbDesc();

    Movie findByName(String name);

    void deleteMovieById(Long id);
}

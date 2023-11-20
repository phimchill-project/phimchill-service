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

}

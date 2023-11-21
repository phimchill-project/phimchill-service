package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Movie;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
=======
import com.codegym.phimchill.entity.TVSeries;
>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.isRelease = false")
    List<Movie> findUnreleasedMovies();
<<<<<<< HEAD
    @Query("SELECT movie FROM Movie movie JOIN movie.categoryList c WHERE c.id = :categoryId")
    List<Movie> findMoviesByCategoryId(Long categoryId);
    @Query("SELECT m FROM Movie m ORDER BY m.views DESC")
    Page<Movie> findTop10ByOrderByViewsDesc(Pageable pageable);

=======
    List<Movie> findFirst10ByOrderByImdbDesc();
>>>>>>> 5286e9eb94fa522937fd07b6b60ea86eb6b73a1d
}

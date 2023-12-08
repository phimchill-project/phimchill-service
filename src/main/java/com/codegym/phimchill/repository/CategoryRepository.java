package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @Query(value = "SELECT * FROM movie " +
//            "JOIN category_movie ON movie.id = category_movie.movie_id " +
//            "JOIN category ON category.id = category_movie.category_id " +
//            "WHERE category.id = :categoryId",
//            nativeQuery = true)
}

package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

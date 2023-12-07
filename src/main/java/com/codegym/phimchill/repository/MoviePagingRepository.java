package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MoviePagingRepository extends PagingAndSortingRepository<Movie, Long> {
    Page<Movie> findAll(Pageable pageable);

}

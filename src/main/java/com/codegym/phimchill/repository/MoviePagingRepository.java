package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MoviePagingRepository extends PagingAndSortingRepository<Movie, Long> {

}

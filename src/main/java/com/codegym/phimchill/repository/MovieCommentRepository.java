package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
    List<MovieComment> findAllByMovieId(Long movieId);
}

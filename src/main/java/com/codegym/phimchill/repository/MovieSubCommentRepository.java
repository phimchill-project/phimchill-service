package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.MovieSubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSubCommentRepository extends JpaRepository<MovieSubComment, Long> {
    List<MovieSubComment> findMovieSubCommentsByMovieComments_Id(long id);
}
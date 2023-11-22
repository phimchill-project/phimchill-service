package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCommentRepository extends JpaRepository<MovieComment, Long> {
}

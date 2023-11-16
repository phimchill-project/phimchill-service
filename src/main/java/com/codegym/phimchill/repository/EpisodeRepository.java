package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}

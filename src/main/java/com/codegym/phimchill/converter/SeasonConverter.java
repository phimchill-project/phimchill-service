package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.SeasonDto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.entity.Season;

public interface SeasonConverter {
    SeasonDto convertToDto(Season season);
}

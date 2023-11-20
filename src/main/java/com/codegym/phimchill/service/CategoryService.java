package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.response.CategoryListResponse;
import com.codegym.phimchill.dto.payload.response.CategoryResponse;
import com.codegym.phimchill.entity.Category;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);

    CategoryListResponse getAll() throws Exception;
}

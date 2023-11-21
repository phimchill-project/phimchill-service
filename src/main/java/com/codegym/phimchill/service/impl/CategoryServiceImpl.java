package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.repository.CategoryRepository;
import com.codegym.phimchill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}

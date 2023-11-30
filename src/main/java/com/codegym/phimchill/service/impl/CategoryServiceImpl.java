package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.CategoryConverter;
import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.payload.response.CategoryListResponse;
import com.codegym.phimchill.dto.payload.response.CategoryResponse;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.repository.CategoryRepository;
import com.codegym.phimchill.service.CategoryService;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TvSeriesService tvSeriesService;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryListResponse getAll() throws Exception {
        List<Category> categoryList = categoryRepository.findAll();
        if(categoryList.isEmpty()){
            throw new Exception("Cannot get all categories");
        }
        List<CategoryDto> categoryDtoList = categoryConverter.convertToListDto(categoryList);
        return CategoryListResponse.builder()
                .data(categoryDtoList)
                .message("Get All Categories Success")
                .statusCode(200)
                .build();
    }

    @Override
    public ListMovieResponse findMoviesByCategoryId(Long id) throws Exception {
        return movieService.findMoviesByCategoryId(id);
    }

    @Override
    public ListTvSeriesResponse findTvSeriesByCategoryId(Long id) throws Exception {
        return tvSeriesService.findTvSeriesByCategoryId(id);
    }

}

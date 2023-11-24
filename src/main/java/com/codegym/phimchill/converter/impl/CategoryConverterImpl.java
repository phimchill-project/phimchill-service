package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.CategoryConverter;
import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.Category;
import com.codegym.phimchill.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> convertToListDto(List<Category> categoryList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryList){
            categoryDtoList.add(convertToDto(category));
        }
        return categoryDtoList;
    }
}

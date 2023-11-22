package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.CategoryDto;
import com.codegym.phimchill.entity.Category;
import java.util.List;

public interface CategoryConverter {
    CategoryDto convertToDto(Category category);

    List<CategoryDto> convertToListDto (List<Category> categoryList);

}

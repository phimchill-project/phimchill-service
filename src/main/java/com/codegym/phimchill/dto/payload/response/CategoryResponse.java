package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.CategoryDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {
    private CategoryDto data;
    private String message;
    private int statusCode;
}

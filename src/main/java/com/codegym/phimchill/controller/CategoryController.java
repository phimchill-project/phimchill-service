package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.CategoryListResponse;
import com.codegym.phimchill.dto.payload.response.ErrorMessageResponse;
import com.codegym.phimchill.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategory() {
        try {
            CategoryListResponse response = categoryService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ErrorMessageResponse response = new ErrorMessageResponse();
            response.setMessage(e.getMessage());
            response.setStatusCode(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}

package com.praveen.expense_tracker_app.service;

import com.praveen.expense_tracker_app.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(Long id,String categoryName);

    void deleteCategory(Long id);
}

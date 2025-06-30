package com.praveen.expense_tracker_app.mapper;

import com.praveen.expense_tracker_app.dto.CategoryDto;
import com.praveen.expense_tracker_app.entity.Category;

public class CategoryMapper {

    public static Category mapToCategory(CategoryDto categoryDto){
        Category category = new Category(
                categoryDto.id(),
                categoryDto.categoryName()
        );
        return category;
    }

    public static  CategoryDto mapToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto(
                category.getId(),
                category.getCategoryName()
        );
        return categoryDto;
    }
}

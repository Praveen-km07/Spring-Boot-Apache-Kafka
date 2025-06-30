package com.praveen.expense_tracker_app.service.impl;

import com.praveen.expense_tracker_app.dto.CategoryDto;
import com.praveen.expense_tracker_app.entity.Category;
import com.praveen.expense_tracker_app.mapper.CategoryMapper;
import com.praveen.expense_tracker_app.repository.CategoryRepository;
import com.praveen.expense_tracker_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    /**
     * @param categoryDto
     * @return
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category does not exists with id : "+id));
        return CategoryMapper.mapToCategoryDto(category);
    }

    /**
     * @return
     */
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @param categoryName
     * @return
     */
    @Override
    public CategoryDto updateCategory(Long id, String categoryName) {
        Category category=categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category ID doesnot exists with ID : "+id));
        category.setCategoryName(categoryName);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    /**
     * @param id
     */
    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category ID does not exisits"));
        categoryRepository.delete(category);
    }
}

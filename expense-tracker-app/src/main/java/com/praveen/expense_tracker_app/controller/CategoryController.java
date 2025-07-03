package com.praveen.expense_tracker_app.controller;

import com.praveen.expense_tracker_app.dto.CategoryDto;
import com.praveen.expense_tracker_app.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
@Tag(name="Expense-Tracker-App",description = "Operation Related to Category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Creating the category",responses = {
            @ApiResponse(responseCode = "200",description = "Category creation is successful"),
            @ApiResponse(responseCode = "201",description = "Category creation is successful"),
            @ApiResponse(responseCode = "400",description = "Category creation is unsuccessful")
    })
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
      return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    //Get specific category by id
    @GetMapping("/{id}")
    @Operation(summary="Getting specific category by id")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        CategoryDto categoryDto =categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    //Get All category
    @GetMapping
    @Operation(summary = "Get All Categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories=categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    //Update the category
    @PutMapping("/{id}")
    @Operation(summary = "Update the existing categories",responses = {
            @ApiResponse(responseCode = "200",description = "Updating the category is successful"),
            @ApiResponse(responseCode = "400",description = "Updating the category is unsuccessful")
    })
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        CategoryDto updateCategoryDto = categoryService.updateCategory(id,categoryDto.categoryName());
        return ResponseEntity.ok(updateCategoryDto);
    }

    //Delete the category
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the category",responses = {
            @ApiResponse(responseCode = "200",description = "Deletion of category is successful"),
            @ApiResponse(responseCode = "400",description = "Deletion of category is unsuccessful")
    })
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deletion is successful");
    }
}

package com.nimap.demo.controller;

import com.nimap.demo.entity.Category;
import com.nimap.demo.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1. GET all the categories (with pagination)
    @GetMapping("/getAll")
    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page, 
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<Category> categories = categoryService.getAllCategories(PageRequest.of(page, size));
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // 2. POST - create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.registerCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // 3. GET category by Id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // 4. PUT - update category by id
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategoryById(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // 5. DELETE - Delete category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // POST - create multiple categories with products
    @PostMapping("/multipleCategories")
    public ResponseEntity<List<Category>> createCategoriesWithProducts(@RequestBody List<Category> categories) {
        List<Category> createdCategories = categoryService.createCategoriesWithProducts(categories);
        return new ResponseEntity<>(createdCategories, HttpStatus.CREATED);
    }

}

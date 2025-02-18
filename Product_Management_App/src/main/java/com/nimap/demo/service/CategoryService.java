package com.nimap.demo.service;

import java.util.List;  // Make sure this import is here

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nimap.demo.entity.Category;

public interface CategoryService {

	// GET all the categories (with pagination)
	public Page<Category> getAllCategories(Pageable pageable);

	// POST - create a new category
	public Category registerCategory(Category category);

	// GET category by Id
	public Category getCategoryById(Long id);

	// Adds a new category
	public Category addNewCategory(Category category);

	// DELETE - Delete category by id
	void deleteCategoryById(long id);

	// PUT - update category by id
	public Category updateCategoryById(Long id, Category category);

	// Creating categories with products
	List<Category> createCategoriesWithProducts(List<Category> categories);

}

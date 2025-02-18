package com.nimap.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.demo.entity.Category;
import com.nimap.demo.repository.CategoryRepository;
import com.nimap.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<Category> getAllCategories(Pageable pageable) {
		// This returns categories based on the provided page number and size.
		return categoryRepository.findAll(pageable);
	}

	@Override
	public Category registerCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
	}

	@Override
	public Category addNewCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> createCategoriesWithProducts(List<Category> categories) {
		return categoryRepository.saveAll(categories);
	}

	@Override
	public Category updateCategoryById(Long id, Category category) {
		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

		existingCategory.setName(category.getName());

		return categoryRepository.save(existingCategory);
	}
}

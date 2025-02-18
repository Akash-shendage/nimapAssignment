package com.nimap.demo.serviceimpl;

import com.nimap.demo.entity.Product;
import com.nimap.demo.repository.ProductRepository;
import com.nimap.demo.service.ProductService;
import com.nimap.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product updateProductById(Long id, Product product) {
		if (productRepository.existsById(id)) {
			product.setId(id);
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> createProductsWithCategories(List<Product> products) {
		for (Product product : products) {
			// Check if the category exists before saving the product
			if (product.getCategory() != null) {
				// Verify that the category exists
				if (!categoryRepository.existsById(product.getCategory().getId())) {
					throw new RuntimeException(
							"Category with ID " + product.getCategory().getId() + " does not exist.");
				}
			}
		}

		return productRepository.saveAll(products);
	}
}

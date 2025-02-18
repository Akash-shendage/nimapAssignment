package com.nimap.demo.controller;

import com.nimap.demo.entity.Product;
import com.nimap.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// 1. GET all the products with pagination
	@GetMapping("/getAll")
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<Product> products = productService.getAllProducts(PageRequest.of(page, size));
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	// 2. POST - create a new product
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}

	// 3. GET product by Id
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	// 4. PUT - update product by id
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product) {
		Product updatedProduct = productService.updateProductById(id, product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	// 5. DELETE - Delete product by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// 6. POST - Create multiple products with categories (bulk creation)
	@PostMapping("/bulk")
	public ResponseEntity<List<Product>> createProductsWithCategories(@RequestBody List<Product> products) {
		List<Product> createdProducts = productService.createProductsWithCategories(products);
		return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
	}
}

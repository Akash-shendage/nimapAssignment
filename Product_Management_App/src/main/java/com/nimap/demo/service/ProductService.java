package com.nimap.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nimap.demo.entity.Product;
import java.util.List;

public interface ProductService {

    // GET all the products with pagination
    Page<Product> getAllProducts(Pageable pageable);

    // POST - create a new product
    Product createProduct(Product product);

    // GET product by Id
    Product getProductById(Long id);

    // PUT - update product by id
    Product updateProductById(Long id, Product product);

    // DELETE - Delete product by id
    void deleteProductById(Long id);

    // Create multiple products with their categories
    List<Product> createProductsWithCategories(List<Product> products);
}

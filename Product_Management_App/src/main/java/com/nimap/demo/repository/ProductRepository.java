package com.nimap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimap.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

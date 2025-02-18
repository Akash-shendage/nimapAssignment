package com.nimap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimap.demo.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{

}

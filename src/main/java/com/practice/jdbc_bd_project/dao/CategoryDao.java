package com.practice.jdbc_bd_project.dao;

import com.practice.jdbc_bd_project.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    List<Category> findAll();
    Optional<Category> findById(int id);
    Category create(Category category);
}

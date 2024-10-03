package com.practice.jdbc_bd_project.dao;

import com.practice.jdbc_bd_project.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll();
    Optional<Product> findById(int id);
    Product create(Product product);
}

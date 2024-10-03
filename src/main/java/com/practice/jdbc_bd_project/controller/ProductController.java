package com.practice.jdbc_bd_project.controller;

import com.practice.jdbc_bd_project.dao.ProductDao;
import com.practice.jdbc_bd_project.exception.NotFoundException;
import com.practice.jdbc_bd_project.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productDao.findById(id).orElseThrow(() -> new NotFoundException("Не найден товар с id: " + id));
    }

    @GetMapping
    public List<Product> findAll() {
        return productDao.findAll();
    }
    @PostMapping
    public Product create(@RequestBody Product product){
        return productDao.create(product);
    }
}
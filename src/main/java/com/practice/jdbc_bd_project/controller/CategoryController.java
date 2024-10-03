package com.practice.jdbc_bd_project.controller;

import com.practice.jdbc_bd_project.dao.CategoryDao;
import com.practice.jdbc_bd_project.exception.NotFoundException;
import com.practice.jdbc_bd_project.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryDao categoryDao;

    @GetMapping("/{id}")
    public Category findById(@PathVariable int id) {
        return categoryDao.findById(id).orElseThrow(() -> new NotFoundException("Не найдена категория с id: " + id));
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
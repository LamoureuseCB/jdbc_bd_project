package com.practice.jdbc_bd_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
}

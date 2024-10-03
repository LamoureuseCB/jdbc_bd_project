package com.practice.jdbc_bd_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private int  categoryId;
}

package com.practice.jdbc_bd_project.dao;

import com.practice.jdbc_bd_project.model.Category;
import com.practice.jdbc_bd_project.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private  final String select ="""
                      select products.id as productId,
                       products.name as productName,
                        categories.id as categoryId,
            categories.name as categoryName,
                         products.price as productPrice
                      from categories join products
                      on  categories.id = products.category_id
            
            """;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(select, this::mapRaw);
    }

    @Override
    public Optional<Product> findById(int id) {
        String sql = "select * from products where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRaw, id));
    }

    @Override
    public Product create(Product product) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("price", product.getPrice());
        map.put("category_id", product.getCategory().getId());
        int id = insert.executeAndReturnKey(map).intValue();
        return new Product(id, product.getName(), product.getPrice(),product.getCategory());
    }

    private Product mapRaw(ResultSet rs, int rowNum) throws SQLException {
        int productId = rs.getInt("productId");
        String productName = rs.getString("productName");
        double productPrice = rs.getDouble("productPrice");
       String categoryName = rs.getString("categoryName");
       int categoryId = rs.getInt("categoryId");
        Category category = new Category(categoryId,categoryName);
        return new Product(productId, productName, productPrice,category);
    }
}

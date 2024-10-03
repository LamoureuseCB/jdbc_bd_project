package com.practice.jdbc_bd_project.dao;

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

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", this::mapRaw);
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
        map.put("name", product.getProductName());
        map.put("price", product.getProductPrice());
        map.put("category_id", product.getCategoryId());
        int id = insert.executeAndReturnKey(map).intValue();
        return new Product(id, product.getProductName(), product.getProductPrice(), product.getCategoryId());
    }

    private Product mapRaw(ResultSet rs, int rowNum) throws SQLException {
        int productId = rs.getInt("id");
        String productName = rs.getString("name");
        double productPrice = rs.getDouble("price");
        int categoryId = rs.getInt("category_id");
        return new Product(productId, productName, productPrice, categoryId);
    }
}

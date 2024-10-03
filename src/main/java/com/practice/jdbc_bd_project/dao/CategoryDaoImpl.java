package com.practice.jdbc_bd_project.dao;

import com.practice.jdbc_bd_project.model.Category;
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
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("select * from categories", this::mapRaw);
    }

    @Override
    public Optional<Category> findById(int id) {
        String sql = "select * from categories where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRaw, id));
    }

    @Override
    public Category create(Category category) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("categories")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> map = new HashMap<>();
        map.put("name", category.getName());
        int categoryId = insert.executeAndReturnKey(map).intValue();
        return new Category(categoryId, category.getName());
    }

    private Category mapRaw(ResultSet rs, int rowNum) throws SQLException {
        int categoryId = rs.getInt("id");
        String categoryName = rs.getString("name");
        return new Category(categoryId, categoryName);
    }
}


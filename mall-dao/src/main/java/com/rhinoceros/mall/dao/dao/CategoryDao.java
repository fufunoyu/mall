package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}

package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有产品分类
     *
     * @return
     */
    List<Category> findAll();

    List<Category> findRootCategories();

    List<Category> findByParentId(Long parentId);

    int add(Category category);
}

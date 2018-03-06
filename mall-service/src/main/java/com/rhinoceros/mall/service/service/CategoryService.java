package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.pojo.Category;

import java.util.List;

/**
 * 分类service
 */
public interface CategoryService {

    /**
     * 查询所有分类
     *
     * @return 分类列表
     */
    List<Category> findAll();

    List<Category> findRootCategories();

    List<Category> findByParentId(Long parentId);

    Category add(Category category);
}

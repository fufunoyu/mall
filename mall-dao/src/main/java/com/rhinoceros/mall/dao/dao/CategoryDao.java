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

    /**
     * 根据id查找分类
     *
     * @param id
     * @return
     */
    Category findById(Long id);
}

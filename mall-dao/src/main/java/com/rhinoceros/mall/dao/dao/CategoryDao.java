package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.po.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有产品分类
     *
     * @return
     */
    List<Category> findAll();

    /**
     * 查找根分类
     *
     * @return
     */
    List<Category> findRootCategories();

    /**
     * 根据分类id查找一级子分类
     *
     * @param id
     * @return
     */
    List<Category> findChildrenById(Long id);

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    int add(Category category);

    /**
     * 根据id查找分类
     *
     * @param id
     * @return
     */
    Category findById(Long id);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id更新分类中不为null的字段
     *
     * @param category
     * @return
     */
    int updateSelectionById(Category category);
}

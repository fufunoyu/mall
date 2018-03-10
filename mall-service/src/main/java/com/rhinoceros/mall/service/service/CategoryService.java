package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.po.Category;

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

    /**
     * 根据id查找分类
     *
     * @param id
     * @return
     */
    Category findById(Long id);

    /**
     * 查询根分类
     *
     * @return
     */
    List<Category> findRootCategories();

    /**
     * 根据分类id查询直接子分类列表
     *
     * @param id
     * @return
     */
    List<Category> findChildrenById(Long id);

    /**
     * 根据id查询子分类及其下所有分类的列表
     *
     * @param id
     * @return
     */
    List<Category> findChildrenAndBelowById(Long id);

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    Category add(Category category);

    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteById(Long id);

    Category updateSelection(Category category);
}

package com.rhinoceros.mall.manager.manager;

import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;

import java.util.List;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/08 14:14
 * 商品相关的中间处理层
 */
public interface ProductManager {

    /**
     * 查询在某个分类及其子分类下的所有商品
     *
     * @param categoryId 分类id 为null时可以查询所有的商品
     * @param pageQuery  分页条件
     * @return
     */
    List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery);

    /**
     * 添加用户
     *
     * @param product
     * @return
     */
    int add(Product product);

    /**
     * 根据id删除商品
     *
     * @param id
     * @return
     */
    int deleteById(Long id);


    /**
     * 更新所有不为null的字段
     *
     * @param product
     * @return
     */
    int updateSelectionById(Product product);

    /**
     * 根据id查询商品
     *
     * @param id
     * @return
     */
    Product findById(Long id);

    /**
     * 根据查询字段查找符合条件的商品，支持分页
     *
     * @param queryString
     * @param pageQuery
     * @return
     */
    List<Product> query(String queryString, PageQuery pageQuery);

    /**
     * 统计指定查询条件查询到商品总数
     *
     * @param queryString
     * @return
     */
    Long countQuery(String queryString);

    /**
     * 根据分类id查找该分类下及其子分类下的所有产品的总数
     *
     * @param categoryId
     * @return
     */
    Long countDeepByCategoryId(Long categoryId);
}

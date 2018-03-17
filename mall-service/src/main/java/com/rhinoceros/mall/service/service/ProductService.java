package com.rhinoceros.mall.service.service;
/* created at 4:11 PM 2/28/2018  */


import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;

import java.util.List;

/**
 * 查询商品信息
 */
public interface ProductService {
    /**
     * 根据id查询商品信息
     *
     * @param id 商品id号
     * @return 商品信息
     */
    Product findById(Long id);

    /**
     * 根据分类id和分页条件查找该分类下及其子分类下的所有产品
     *
     * @param categoryId 分类id
     * @param pageQuery  分页条件
     * @return
     */
    List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery);

    /**
     * 根据条件分页查询商品列表
     *
     * @param query
     * @param pageQuery
     * @return
     */
    List<Product> query(String query, PageQuery pageQuery);

    /**
     * 获取指定条件查询出的数据总数
     *
     * @param query
     * @return
     */
    Long countQuery(String query);

    /**
     * 根据分类id查找该分类下及其子分类下的所有产品的总数
     *
     * @param categoryId
     * @return
     */
    Long countDeepByCategoryId(Long categoryId);

    void deleteById(Long id);

    void updateSelectionById(Product product);
}

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

    List<Product> findAll(PageQuery pageQuery);

    Long countDeepByCategoryId(Long categoryId);
}

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

}

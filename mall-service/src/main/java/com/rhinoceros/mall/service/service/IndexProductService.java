package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexProduct;

import java.util.List;

public interface IndexProductService {

    /**
     * 查找全部主页展示的商品
     * @return
     */
    List<CategoryWithProducts> findAll();

    /**
     * 根据根分类ID查找主页展示商品
     * @param categoryId
     * @return
     */
    List<IndexProduct> findByCategoryId(Long categoryId);

    /**
     * 添加主页展示商品
     * @param indexProduct
     * @return
     */
    IndexProduct add(IndexProduct indexProduct);

    /**
     * 删除主页展示商品
     * @param indexProductId
     */
    void deleteById(Long indexProductId);
}

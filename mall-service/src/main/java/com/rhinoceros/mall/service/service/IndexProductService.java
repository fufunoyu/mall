package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexProduct;

import java.util.List;

public interface IndexProductService {

    /**
     * 查找全部主页展示的商品
     *
     * @return
     */
    List<CategoryWithProducts> findAll();

    /**
     * 添加主页展示商品
     *
     * @param indexProduct
     * @return
     */
    IndexProduct add(IndexProduct indexProduct);

    /**
     * 根据商品id删除主页展示商品
     *
     * @param productId
     */
    void deleteByProductId(Long productId);
}

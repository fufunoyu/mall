package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;

import java.util.List;

public interface ProductService {
    /**
     * 根据分类id和分页条件查找产品列表
     *
     * @param categoryId 分类id
     * @param pageQuery  分页条件
     * @return
     */
    List<Product> findByCategoryId(Long categoryId, PageQuery pageQuery);
}

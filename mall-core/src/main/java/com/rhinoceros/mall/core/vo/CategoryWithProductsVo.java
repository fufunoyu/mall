package com.rhinoceros.mall.core.vo;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 封装分类及其产品信息
 */
@Getter
@Setter
public class CategoryWithProductsVo {

    /**
     * 分类下的产品列表
     */
    private List<Product> products;

    /**
     * 分类信息
     */
    private Category category;

}

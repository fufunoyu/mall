package com.rhinoceros.mall.core.dto;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.core.pojo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategoryWithProductsDto {
    /**
     * 分类下的产品列表
     */
    private List<Product> products;

    /**
     * 分类信息
     */
    private Category category;
}

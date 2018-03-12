package com.rhinoceros.mall.core.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;

/**
 * 包含分类下商品列表的实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryWithProducts extends Category {

    private List<Product> products = new LinkedList<Product>();
}

package com.rhinoceros.mall.core.pojo;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 包含分类下商品列表的实体类
 */
@Data
public class CategoryWithProducts extends Category{

    private List<Product> products = new LinkedList<Product>();
}

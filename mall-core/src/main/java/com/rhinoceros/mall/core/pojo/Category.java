package com.rhinoceros.mall.core.pojo;

import lombok.Data;

/**
 * 商品分类实体
 */
@Data
public class Category {
    /**
     * 商品分类id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 上级分类id
     */
    private Long parentId;
}

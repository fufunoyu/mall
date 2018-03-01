package com.rhinoceros.mall.core.vo;

import com.rhinoceros.mall.core.pojo.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * 商品分类的封装
 */
@Getter
@Setter
public class CategoryVo {

    /**
     * 分类信息
     */
    private Category category;

    /**
     * 子分类
     */
    private List<CategoryVo> children = new LinkedList<CategoryVo>();

}

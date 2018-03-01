package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.vo.CategoryVo;

import java.util.List;

/**
 * 分类service
 */
public interface CategoryService {

    /**
     * 查询所有分类
     *
     * @return 分类列表
     */
    List<CategoryVo> findAll();
}

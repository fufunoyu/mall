package com.rhinoceros.mall.dao.dao;


import com.rhinoceros.mall.core.po.IndexSlider;

import java.util.List;

/**
 * 首页轮播图
 */
public interface IndexSliderDao {

    /**
     * 查找全部轮播图信息
     *
     * @return
     */
    List<IndexSlider> findAll();

    /**
     * 添加轮播图
     *
     * @param indexSlider
     * @return
     */
    int add(IndexSlider indexSlider);

    /**
     * 删除轮播图
     *
     * @param id
     */
    void deleteById(Long id);
}

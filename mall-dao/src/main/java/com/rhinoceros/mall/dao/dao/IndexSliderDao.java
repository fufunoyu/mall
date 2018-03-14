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
     * 根据标题查找
     * @param title
     * @return
     */
    IndexSlider findByTitle(String title);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    IndexSlider findById(Long id);

    /**
     * 添加轮播图
     *
     * @param indexSlider
     * @return
     */
    int add(IndexSlider indexSlider);

    /**
     * 根据id删除轮播图
     *
     * @param id
     */
    void deleteById(Long id);
}

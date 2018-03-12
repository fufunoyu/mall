package com.rhinoceros.mall.service.service;
import com.rhinoceros.mall.core.po.IndexSlider;

import java.util.List;

public interface IndexSliderService {

    /**
     * 查找所有轮播图
     * @return
     */
    List<IndexSlider> findAll();

    /**
     * 添加
     * @param indexSlider
     * @return
     */
    IndexSlider add(IndexSlider indexSlider);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

}

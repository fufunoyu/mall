package com.rhinoceros.mall.dao.dao;


import com.rhinoceros.mall.core.po.Slideshow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SlideshowDao {

    /**
     * 查找全部轮播图信息
     * @return
     */
    List<Slideshow> findAll();

    /**
     * 添加轮播图
     * @param slideshow
     * @return
     */
    int add(Slideshow slideshow);

    /**
     * 删除轮播图
     * @param id
     */
    void deleteById(Long id);
}

package com.rhinoceros.mall.service.service;
import com.rhinoceros.mall.core.po.Slideshow;
import java.util.List;

public interface SlideshowService {

    /**
     * 查找所有轮播图
     * @return
     */
    List<Slideshow> findAll();

    /**
     * 添加
     * @param slideshow
     * @return
     */
    Slideshow add(Slideshow slideshow);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

}

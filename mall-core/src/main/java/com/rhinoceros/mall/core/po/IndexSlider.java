package com.rhinoceros.mall.core.po;
import lombok.Data;
@Data
/**
 * 轮播图实体类
 */
public class IndexSlider {

    private Long id;

    /**
     * 图片标题
     */
    private String title;

    /**
     * 轮播URL
     */
    private String imageUrl;

    /**
     * 点击轮播图跳转URL
     */
    private String jumpUrl;

}

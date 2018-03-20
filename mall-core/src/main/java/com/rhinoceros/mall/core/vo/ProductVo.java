package com.rhinoceros.mall.core.vo;
/* created at 3:27 PM 2/28/2018  */

import com.rhinoceros.mall.core.po.Product;
import lombok.Data;

/**
 * 这个类封装商品展示信息
 */
@Data
public class ProductVo {

    /**
     * 父类
     */
    private Product product;

    /**
     * 这个变量储存商品图片地址的数组
     */
    private String[] imagesUrls;

    /**
     * 这变量储存商品第一张图片的地址
     */
    private String firstImageUrl;
    /**
     * 分类名称
     */
    private String categoryName;

    public ProductVo(Product product) {
        this.product = product;
        //获取商品图片url数组
        if(product.getImageUrls()!=null){
            imagesUrls = product.getImageUrls().split(Product.IMAGE_SEPARATION);
            firstImageUrl = imagesUrls[0];
        }
    }


}

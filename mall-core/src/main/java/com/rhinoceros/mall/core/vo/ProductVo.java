package com.rhinoceros.mall.core.vo;
/* created at 3:27 PM 2/28/2018  */

import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.ProductDescription;
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
     * 商品详情
     */
    private ProductDescription productDescription;

    public ProductVo(Product product){
        this.product = product;
        //获取商品图片url数组
        imagesUrls = product.getImageUrls().split(Product.IMAGE_SEPARATION);
        firstImageUrl = imagesUrls[0];
    }


    /**
     * 分类名称
     */
    private String categoryName;
    private String description;

//    /**
//     * 此变量储存商品详情图片的url
//     */
//    private String[] descriptionImagesUrls;
//
//    /**
//     * 配置信息
//     */
//    private Map<String, String> params = new HashMap<String, String>();

}

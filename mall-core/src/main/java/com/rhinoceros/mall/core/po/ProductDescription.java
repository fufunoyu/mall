package com.rhinoceros.mall.core.po;
/* created at 2:28 PM 3/7/2018  */

import lombok.Data;

@Data
public class ProductDescription {

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 详情内容
     */
    private String description;
}

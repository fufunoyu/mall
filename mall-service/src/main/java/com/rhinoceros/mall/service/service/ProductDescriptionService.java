package com.rhinoceros.mall.service.service;
/* created at 3:28 PM 3/9/2018  */

import com.rhinoceros.mall.core.po.ProductDescription;

public interface ProductDescriptionService {

    /**
     * 根据商品id获取商品详情描述
     * @param id
     * @return
     */
    ProductDescription findByProductId(Long id);
}

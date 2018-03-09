package com.rhinoceros.mall.dao.dao;
/* created at 3:57 PM 3/7/2018  */

import com.rhinoceros.mall.core.pojo.ProductDescription;

public interface ProductDescriptionDao {

    /**
     * 根据产品id查找产品详情
     * @param productId
     * @return
     */
    ProductDescription findByProductId(Long productId);
}

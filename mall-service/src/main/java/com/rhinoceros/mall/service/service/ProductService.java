package com.rhinoceros.mall.service.service;
/* created at 4:11 PM 2/28/2018  */


import com.rhinoceros.mall.core.vo.ProductVo;

/**
 * 查询商品信息
 */
public interface ProductService {
    /**
     * 根据id查询商品信息
     * @param id 商品id号
     * @return 商品信息
     */
    ProductVo findProductVoById(Long id);
}

package com.rhinoceros.mall.service.service;


import com.rhinoceros.mall.core.pojo.CartProduct;
import com.rhinoceros.mall.core.pojo.Product;

import java.util.List;

/**
 * 购物车业务逻辑接口
 */
public interface CartProductService {
    /**
     * 找到购物车
     * @return
     */
    List<CartProduct> findByUserId(Long userId);

    /**
     * 浏览购物车信息
     * @param productId
     * @return
     */
  //  List<Product> findByProductId(Long productId);
}

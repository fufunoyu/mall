package com.rhinoceros.mall.service.service;


import com.rhinoceros.mall.core.po.CartProduct;

import java.util.List;

/**
 * 购物车业务逻辑接口
 */
public interface CartProductService {
    /**
     * 获取用户的购物车中的商品信息
     * @return
     */
    List<CartProduct> findByUserId(Long userId);


    /**
     * 从购物车删除商品信息
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改购物车中商品数量
     * @param cartProduct
     * @return
     */
    Integer updateSelectionById(CartProduct cartProduct);

    /**
     * 添加商品到购物车
     * @param productId
     * @param userId
     * @param ProductNum
     */
    void add(Long productId, Long userId, Integer ProductNum);

    /**
     * 购物车结算到订单确认页面
     * @param ids
     * @param userId
     * @return
     */
    List<CartProduct> findCartProducts(List<Long>ids, Long userId);
}

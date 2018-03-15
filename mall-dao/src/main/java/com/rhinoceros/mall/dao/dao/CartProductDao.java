package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.po.CartProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartProductDao {
    /**
     * 根据用户id找到对应购物车
     *
     * @param userId
     * @return
     */
    List<CartProduct> findByUserId(Long userId);

    /**
     * 删除购物车商品
     *
     * @param cartProductId
     */
    int deleteById(Long cartProductId);

    /**
     * 更新购物车中不为null的字段
     *
     * @param cartProduct
     * @return
     */
    int updateSelectionById(CartProduct cartProduct);

    /**
     * 向购物车中添加商品
     *
     * @param cartProduct
     * @return 修改条目数目
     */
    int add(CartProduct cartProduct);

    /**
     * 根据用户id和商品id查找购物车中商品
     *
     * @param userId
     * @param productId
     * @return
     */
    CartProduct findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * 根据id查询购物车，不存在返回null
     *
     * @param id
     * @return
     */
    CartProduct findById(Long id);
}
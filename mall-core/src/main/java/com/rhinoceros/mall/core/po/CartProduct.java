package com.rhinoceros.mall.core.po;
import lombok.Data;

@Data
/**
 *购物车商品关系
 */
public class CartProduct {
    /**
     * id
     */
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品购买数量
     */
    private Integer productNum;
}

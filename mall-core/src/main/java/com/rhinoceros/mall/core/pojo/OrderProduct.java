package com.rhinoceros.mall.core.pojo;

import lombok.Data;

/**
 * 订单商品关系
 */
@Data
public class OrderProduct {
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 单个商品购买数量
     */
    private Integer productNum;
}

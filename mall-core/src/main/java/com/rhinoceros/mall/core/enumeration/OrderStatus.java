package com.rhinoceros.mall.core.enumeration;

/**
 * 订单状态
 */
public enum OrderStatus {
    /**
     * 待支付
     */
    WAIT_PAY,
    /**
     * 已支付
     */
    PAYED,
    /**
     * 已发货
     */
    SHIPPED,

    /**
     * 已完成
     */
    COMPLETED,

    /**
     * 取消
     */
    CANCEL,

    /**
     * 待评价
     */
    WAIT_COMMENT
}

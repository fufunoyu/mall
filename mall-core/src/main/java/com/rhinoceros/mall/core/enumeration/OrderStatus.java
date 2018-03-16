package com.rhinoceros.mall.core.enumeration;

/**
 * 订单状态,
 * 订单状态的走向
 * 待支付--(完成支付)->待发货--(完成发货)-->待收货--(完成收货或者一段时间后)-->待评价--(评价完成或者一段时间后)-->已完成
 *
 * 待收货-->退货受理中（待退货）-->退货中-->退货成功(退款）
 *
 * 中途任意环节出问题，订单状态都变为取消(CANCEL)
 */
public enum OrderStatus {
    /**
     * 待支付
     */
    WAIT_PAY,
    /**
     * 待发货
     */
    WAIT_SHIP,
    /**
     * 待收货
     */
    WAIT_RECEIVE,
    /**
     * 待评价
     */
    WAIT_COMMENT,
    /**
     * 待退货
     */
    WAIT_RETURN,
    /**
     * 退货中
     */
    RETURN_ING,
    /**
     * 退货成功
     */
    RETURN_COMPLETED,
    /**
     * 已完成
     */
    COMPLETED,
    /**
     * 取消
     */
    CANCEL
}

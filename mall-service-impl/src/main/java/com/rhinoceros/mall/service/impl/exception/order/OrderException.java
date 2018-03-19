package com.rhinoceros.mall.service.impl.exception.order;
/* created at 3:01 PM 3/17/2018  */

import com.rhinoceros.mall.service.impl.exception.BaseServiceException;

/**
 * 订单异常基类
 */
public class OrderException extends BaseServiceException {
    public OrderException() {
        super();
    }

    public OrderException(String msg) {
        super(msg);
    }
}

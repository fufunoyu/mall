package com.rhinoceros.mall.service.service;
/* created at 10:02 PM 3/17/2018  */

import com.rhinoceros.mall.core.po.Order;

public interface PayService {

    /**
     * 付款
     * @param order
     * @return
     */
    String toPay(Order order);

}

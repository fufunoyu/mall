package com.rhinoceros.mall.service.service;
/* created at 10:02 PM 3/17/2018  */

import com.rhinoceros.mall.core.po.Order;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface PayService {

    /**
     * 付款
     *
     * @param orderIdList
     * @return
     */
    String toPayByOrderList(List<Order> orderIdList);

    /**
     * 支付回调处理
     *
     * @param parameter
     * @param inputStream
     * @return
     */
    List<String> payBack(Map<String, String[]> parameter, InputStream inputStream);

}

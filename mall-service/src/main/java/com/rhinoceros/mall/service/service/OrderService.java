package com.rhinoceros.mall.service.service;
/* created at 8:10 PM 3/6/2018  */

import com.rhinoceros.mall.core.vo.OrderListVo;

import java.util.List;


public interface OrderService {

    /**
     *
     * @param userId
     * @return
     */
    List<OrderListVo> findOrderListVoByUserId(Long userId, String status);

    List<OrderListVo> findOrderListVoByUserId(Long userId);
}

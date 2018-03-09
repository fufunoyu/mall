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
    List<OrderListVo> findOrderListVoByUserId(Long userId, String status,Integer page,Integer size);

    List<OrderListVo> findOrderListVoByUserId(Long userId,Integer page,Integer size);

    Integer findOrderNumByUserIdAndStatus(Long userId);

    Integer findOrderNumByUserIdAndStatus(Long userId, String status);
}

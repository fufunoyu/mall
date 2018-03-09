package com.rhinoceros.mall.service.service;
/* created at 8:10 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.Order;
import com.rhinoceros.mall.core.vo.OrderListVo;

import java.util.List;


public interface OrderService {

    /**
     * 根据userId和订单状态找出所有符合条件的分页订单
     * @param userId
     * @param status
     * @param page
     * @param size
     * @return
     */
    List<OrderListVo> findOrderListVoByUserId(Long userId, String status,Integer page,Integer size);

    /**
     * 根据userId找出所有符合条件的分页订单
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<OrderListVo> findOrderListVoByUserId(Long userId,Integer page,Integer size);

    /**
     * 根据用户id找出符合条件的订单数量
     * @param userId
     * @return 订单数量
     */
    Integer findOrderNumByUserIdAndStatus(Long userId);

    /**
     * 根据用户id和状态找出符合条件的订单数量
     * @param userId
     * @param status
     * @return 订单数量
     */
    Integer findOrderNumByUserIdAndStatus(Long userId, String status);

    /**
     * 根据订单id更新订单信息
     * @param order
     */
    void updateSelectionById(Order order);
}

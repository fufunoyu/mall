package com.rhinoceros.mall.service.service;
/* created at 8:10 PM 3/6/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.OrderProduct;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.OrderListVo;

import java.util.List;


public interface OrderService {

    /**
     * 根据userId和订单状态找出所有符合条件的分页订单
     * @param userId
     * @param status
     * @param pageQuery
     * @return
     */
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status, PageQuery pageQuery);


    /**
     * 根据用户id和状态找出符合条件的订单数量
     *
     * @param userId
     * @param status
     * @return 订单数量
     */
    Integer countByUserIdAndStatus(Long userId, OrderStatus status);

    /**
     * 根据订单id更新订单信息
     *
     * @param order
     */
    void updateSelectionById(Order order);

    /**
     * 通过orderId查找订单与商品的关系
     * @param orderId
     * @return
     */
    List<OrderProduct> findOrderProductById(Long orderId);
}

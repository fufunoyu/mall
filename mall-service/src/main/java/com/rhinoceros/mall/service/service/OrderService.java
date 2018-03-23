package com.rhinoceros.mall.service.service;
/* created at 8:10 PM 3/6/2018  */

import com.rhinoceros.mall.core.dto.OrderListDto;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.query.PageQuery;

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
     * 根据订单状态找出符合条件的分页订单
     * @param status
     * @param pageQuery
     * @return
     */
    List<Order> findByStatus(OrderStatus status, PageQuery pageQuery);


    /**
     * 根据用户id和状态找出符合条件的订单数量
     * @param userId
     * @param status
     * @return 订单数量
     */
    Integer countByUserIdAndStatus(Long userId, OrderStatus status);

    /**
     * 根据订单id更新订单信息
     * @param order
     */
    void updateSelectionById(Order order);

    /**
     * 根据订单id查找订单
     * @param id
     * @return
     */
    Order findById(Long id);

    /**
     * 添加订单
     *
     * @param dtos
     * @param userId
     * @return
     */
    List<Order> add(OrderListDto dtos, Long userId,Long addressId);
    /**
     * 确认收货
     * @param oid
     */
    void confirmedReceive(Long oid);

    /**
     * 取消订单
     * @param oid
     */
    void cancelOrder(Long oid);

    /**
     * 申请退货
     * @param oid
     */
    void applyReturnOrder(Long oid);



    /**
     * 后台确认退货成功
     * @param oIdentifier
     */
    void confirmReturn(String oIdentifier);

    /**
     * 查找某状态下的订单的总数
     * @param orderStatus
     * @return
     */
    Long countOrderByStatus(OrderStatus orderStatus);

    /**
     * 根据订单号删除订单
     * @param ids
     */
    void updateStatus2ShipByIds(List<Long> ids);
    /**
     * 获取指定条件查询出的数据总数
     *
     * @param status
     * @return
     */
    Long countByStatus(OrderStatus status);
}

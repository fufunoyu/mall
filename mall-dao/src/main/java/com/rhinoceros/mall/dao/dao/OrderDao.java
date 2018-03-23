package com.rhinoceros.mall.dao.dao;
/* created at 4:40 PM 3/6/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.AliPay;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 通过userId查找订单
     * @param userId
     * @return
     */
    List<Order> findByUserId(Long userId);

    /**
     * 根据userId和订单状态查找所有符合要求的订单
     * @param userId
     * @param status    status为null则查询所有状态
     * @param pageQuery
     * @return
     */
    List<Order> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") OrderStatus status, @Param("page")PageQuery pageQuery);


    /**
     * 根据订单状态查找所有符合要求的订单
     * @param status
     * @param pageQuery
     * @return
     */
    List<Order> findByStatus(@Param("status") OrderStatus status, @Param("page")PageQuery pageQuery);

    /**
     * 根据userId和订单状态统计符合要求的订单数目
     * @param userId
     * @param status status为null则查询所有状态
     * @return
     */
    Integer countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") OrderStatus status);

    /**
     * 根据id更新订单信息
     * @param order
     * @return
     */
    int updateSelectionById(Order order);

    /**
     * 根据订单id找订单
     * @param id
     * @return
     */
    Order findById(Long id);

    /**
     * 形成订单
     * @param orders
     * @return
     */
    int addAll(@Param("orders") List<Order> orders);

    /**
     * 根据订单号更新订单信息
     * @param order
     * @return
     */
    int updateSelectionByIdentifier(Order order);

    /**
     * 查找某状态下的订单的总数
     * @param orderStatus
     * @return
     */
    Long countOrderByStatus(@Param("status") OrderStatus orderStatus);


    /**
     * 根据订单号查找订单
     * @param identifier
     * @return
     */
    Order findByIdentifier(String identifier);

    /**
     * 增加支付宝支付信息
     */
    int addAliPay(AliPay aliPay);

    /**
     * 根据订单编号查找支付宝支付信息
     * @param orderIdentifier
     * @return
     */
    AliPay findAliPayByOrderIdentifier(String orderIdentifier);

    /**
     * 更新支付宝订单信息
     * @param aliPay
     * @return
     */
    int updateAliPayByOrderIdentifier(AliPay aliPay);

    List<String> findOIdentifierByTotalId(String totalId);
}

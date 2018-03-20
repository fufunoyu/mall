package com.rhinoceros.mall.dao.dao;
/* created at 4:40 PM 3/6/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
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

}

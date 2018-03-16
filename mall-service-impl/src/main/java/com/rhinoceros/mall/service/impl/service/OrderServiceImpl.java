package com.rhinoceros.mall.service.impl.service;
/* created at 8:11 PM 3/6/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据userId和订单状态找出所有符合条件的分页订单
     *
     * @param userId
     * @param status
     * @param pageQuery
     * @return
     */
    @Override
    public List<Order> findByUserIdAndStatus(Long userId, OrderStatus status, PageQuery pageQuery) {
        return orderDao.findByUserIdAndStatus(userId, status, pageQuery);
    }


    /**
     * 根据用户id和状态找出符合条件的订单数量
     *
     * @param userId
     * @param status
     * @return 订单数量
     */
    @Override
    public Integer countByUserIdAndStatus(Long userId, OrderStatus status) {
        return orderDao.countByUserIdAndStatus(userId, status);
    }

    /**
     * 根据订单id更新订单信息
     *
     * @param order
     */
    @Transactional
    @Override
    public void updateSelectionById(Order order) {
        if (order.getId() == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        orderDao.updateSelectionById(order);
    }

    /**
     * 根据订单id查找订单
     * @param id
     * @return
     */
    @Override
    public Order findById(Long id) {
        return orderDao.findById(id);
    }




}

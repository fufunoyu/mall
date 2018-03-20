package com.rhinoceros.mall.service.impl.service;
/* created at 10:02 PM 3/17/2018  */

import com.egzosn.pay.common.bean.PayOrder;
import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.manager.impl.exception.pay.PayException;
import com.rhinoceros.mall.manager.manager.PayManager;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.order.OrderStatusException;
import com.rhinoceros.mall.service.service.PayService;
import com.rhinoceros.mall.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Map;

@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PayManager payManager;

    @Autowired
    private OrderDao orderDao;

    @Override
    public String toPay(Order order) {
        Product product = productService.findById(order.getProductId());
        String title = "购买 " + product.getName();
        PayOrder payOrder = new PayOrder(title, "摘要", order.getTotalPrice(), order.getIdentifier());
        String string = payManager.toPay(payOrder);
        return string;
    }


    @Transactional
    @Override
    public Long payBack(Map<String, String[]> parameter, InputStream inputStream) {
        Long oid;
        try {
            oid = payManager.payBack(parameter, inputStream);
        } catch (PayException e) {
            throw e;
        }
        if (oid == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        Order order = orderDao.findById(oid);
        if (order == null) {
            log.info("订单不存在");
            throw new EntityNotExistException("订单不存在");
        }
        if (order.getStatus() != OrderStatus.WAIT_PAY ){
            log.info("订单不是待付款状态");
            throw new OrderStatusException("订单不是待付款状态");
        }
        order.setStatus(OrderStatus.WAIT_COMMENT);
        return oid;
    }
}

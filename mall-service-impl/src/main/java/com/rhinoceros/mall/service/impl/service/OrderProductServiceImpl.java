package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.Order;
import com.rhinoceros.mall.core.pojo.OrderProduct;
import com.rhinoceros.mall.dao.dao.OrderProductDao;
import com.rhinoceros.mall.service.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProductServiceImpl implements OrderProductService{
    @Autowired
    private OrderProductDao orderProductDao;

    /**
     * 购买商品形成订单
     * @param productId
     * @param productNum
     */
//    @Override
//    public void addOrderProduct(Long productId, Integer productNum, Long orderId) {
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.setProductId(productId);
//        orderProduct.setOrderId(orderId);
//        orderProduct.setProductNum(productNum);
//        orderProductDao.addOrderProduct(orderProduct);
//    }
}

package com.rhinoceros.mall.service.impl.service;
/* created at 10:02 PM 3/17/2018  */

import com.egzosn.pay.common.bean.PayOrder;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.manager.manager.PayManager;
import com.rhinoceros.mall.service.service.PayService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class PayServiceImpl implements PayService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PayManager payManager;

    @Override
    public String toPay(Order order) {
        Product product = productService.findById(order.getProductId());
        String title = "购买 " + product.getName();
        PayOrder payOrder = new PayOrder(title, "摘要", order.getTotalPrice() , order.getIdentifier());
        return payManager.toPay(payOrder);
    }
}

package com.rhinoceros.mall.dao.dao;
/* created at 4:40 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.OrderProduct;
import com.rhinoceros.mall.core.pojo.Product;

import java.util.List;

public interface OrderProductDao {

    /**
     * 通过orderId查找订单与商品的关系
     * @param orderId
     * @return
     */
    List<OrderProduct> findByOrderId(Long orderId);
    /**
     * 形成订单
     * @param orderProduct
     * @return
     */
    int addOrderProduct(OrderProduct orderProduct);
}

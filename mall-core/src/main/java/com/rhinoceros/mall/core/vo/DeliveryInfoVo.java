package com.rhinoceros.mall.core.vo;

import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DeliveryInfoVo {
    /**
     * 订单信息
     */
    private Order order;
    /**
     * 用户信息
     */
    private User user;

    /**
     * 发货信息
     */
    private Address address;
    /**
     * 商品信息
     */
    private Product product;

}

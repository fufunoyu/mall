package com.rhinoceros.mall.core.vo;
/* created at 4:40 PM 3/6/2018  */

import com.rhinoceros.mall.core.po.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderListVo {

    /**
     * 订单详情
     */
    private Order order;

    /**
     * 订单的商品列表
     */
    private List<OrderProductVo> orderProductVos;



}

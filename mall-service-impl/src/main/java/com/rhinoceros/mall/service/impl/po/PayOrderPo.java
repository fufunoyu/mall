package com.rhinoceros.mall.service.impl.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/*
    created by IntelliJ IDEA.
    User: Heribe Zhou
    Date: 3/23/2018
    Time: 10:44 AM
    */
@Getter
@Setter
public class PayOrderPo {
    /**
     * 支付订单标题
     */
    String title;
    /**
     * 支付订单摘要
     */
    String Body;
    /**
     * 支付订单总金额
     */
    BigDecimal TotalPrice;
    /**
     * 支付订单包含的所有商户订单的订单号
     */
    String IdentifierAll;
}

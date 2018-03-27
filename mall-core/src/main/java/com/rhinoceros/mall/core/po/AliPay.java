package com.rhinoceros.mall.core.po;
/* created at 8:16 PM 3/22/2018  */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AliPay {

    /**
     * 订单编号
     */
    private String orderIdentifier;
    /**
     * 支付宝订单id
     */
    private String tradeNo;
    /**
     * 支付宝退款订单id
     */
    private String returnId;
    /**
     * 订单的总订单号
     */
    private String orderTotalId;
}

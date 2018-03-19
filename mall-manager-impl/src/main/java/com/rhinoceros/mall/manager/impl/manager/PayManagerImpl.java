package com.rhinoceros.mall.manager.impl.manager;
/* created at 8:29 PM 3/17/2018  */

import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayOrder;
import com.rhinoceros.mall.manager.manager.PayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayManagerImpl implements PayManager {

    @Autowired
    private PayService payService;

    @Override
    public String toPay(PayOrder order) {
        //网页支付
        order.setTransactionType(AliTransactionType.DIRECT);
        //获取支付订单信息
        Map orderInfo = payService.orderInfo(order);
        //组装成html表单信息
        return  payService.buildRequest(orderInfo, MethodType.POST);
    }

}

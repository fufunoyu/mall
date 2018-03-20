package com.rhinoceros.mall.manager.impl.manager;
/* created at 8:29 PM 3/17/2018  */

import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayConfigStorage;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayMessage;
import com.egzosn.pay.common.bean.PayOrder;
import com.rhinoceros.mall.core.query.Order;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.manager.impl.exception.pay.PayBackException;
import com.rhinoceros.mall.manager.manager.PayManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Slf4j
@Component
public class PayManagerImpl implements PayManager {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderDao orderDao;

    @Override
    public String toPay(PayOrder order) {
        //网页支付
        order.setTransactionType(AliTransactionType.DIRECT);
        //获取支付订单信息
        Map orderInfo = payService.orderInfo(order);
        //组装成html表单信息
        return payService.buildRequest(orderInfo, MethodType.POST);
    }

    @Override
    public Long payBack(Map<String, String[]> parameter, InputStream inputStream) {
        Map<String,Object> params = payService.getParameter2Map(parameter,inputStream);
        //支付账户配置
        PayConfigStorage storage = payService.getPayConfigStorage();
        if (null == params){
            log.error("支付失败");
            throw new PayBackException(payService.getPayOutMessage("fail","失败").toMessage());
        }
        //校验
        if (!payService.verify(params)){
            log.error("支付失败");
            throw new PayBackException(payService.getPayOutMessage("fail","失败").toMessage());
        }
        //这里处理业务逻辑
        //创建支付消息
        PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());
        Long oid = Long.valueOf(message.getOutTradeNo());
//        return  payService.getPayOutMessage("success", "成功").toMessage();
        return oid;
    }

}

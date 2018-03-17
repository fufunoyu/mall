package com.rhinoceros.mall.manager.impl.manager;
/* created at 8:29 PM 3/17/2018  */

import com.lorne.alipay.config.AliPayConfig;
import com.lorne.alipay.utils.AliPayUtils;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.manager.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderManagerImpl implements OrderManager {

    @Autowired
    private AliPayConfig aliPayConfig;



}

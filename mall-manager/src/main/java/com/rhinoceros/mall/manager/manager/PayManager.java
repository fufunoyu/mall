package com.rhinoceros.mall.manager.manager;
/* created at 8:28 PM 3/17/2018  */

import com.egzosn.pay.common.bean.PayOrder;

public interface PayManager {

   String toPay(PayOrder payOrder);
}

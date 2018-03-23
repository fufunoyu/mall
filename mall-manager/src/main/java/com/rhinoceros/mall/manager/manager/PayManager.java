package com.rhinoceros.mall.manager.manager;
/* created at 8:28 PM 3/17/2018  */

import com.egzosn.pay.common.bean.PayOrder;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface PayManager {

   /**
    * 支付
    * @param payOrder
    * @return
    */
   String aliToPay(PayOrder payOrder);

   /**
    * 支付回调
    * @param parameter
    * @param inputStream
    * @return
    */
   List<String> aliPayBack(Map<String, String[]> parameter, InputStream inputStream);

}

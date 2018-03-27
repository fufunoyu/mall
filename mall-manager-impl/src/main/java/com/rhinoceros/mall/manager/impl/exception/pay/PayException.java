package com.rhinoceros.mall.manager.impl.exception.pay;
/* created at 8:46 PM 3/19/2018  */

import com.rhinoceros.mall.manager.impl.exception.BaseManagerException;

public class PayException extends BaseManagerException {
    public PayException(){
        super();
    }

    public PayException(String msg){
        super(msg);
    }
}

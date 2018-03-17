package com.rhinoceros.mall.service.impl.exception.order;

/**
 * 订单商品数量异常
 */
public class OrderProductNumException extends OrderException{
    public OrderProductNumException(){
        super();
    }
    public OrderProductNumException(String msg){
        super(msg);
    }
}

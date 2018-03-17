package com.rhinoceros.mall.service.impl.exception.order;

public class OrderProductStoreNumException extends OrderException{
    public OrderProductStoreNumException(){
        super();
    }
    public OrderProductStoreNumException(String msg){
        super("商品库存不足");
    }

}

package com.rhinoceros.mall.service.service;
/* created at 2:39 PM 3/6/2018  */

public interface AddressService {

    /**
     * 增加用户的收货信息
     * @param postalCode
     * @param deliveryAddress
     * @param deliveryName
     * @param phone
     * @param userId
     */
    void add(String postalCode,String deliveryAddress, String deliveryName, String phone, Long userId);

}

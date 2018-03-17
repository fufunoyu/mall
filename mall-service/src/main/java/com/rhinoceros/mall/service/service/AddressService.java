package com.rhinoceros.mall.service.service;
/* created at 10:49 AM 3/10/2018  */

import com.rhinoceros.mall.core.po.Address;

import java.util.List;

public interface AddressService {

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Address findById(Long id);

    /**
     * 添加收货地址
     *
     * @param address
     * @return
     */
    Address addDeliveryAddress(Address address);

    List<Address> findByUserId(Long id);
}

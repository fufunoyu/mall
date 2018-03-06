package com.rhinoceros.mall.service.impl.service;
/* created at 2:39 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.Address;
import com.rhinoceros.mall.dao.dao.AddressDao;
import com.rhinoceros.mall.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public void add(String postalCode,String deliveryAddress, String deliveryName, String phone, String userId) {
        Address address = new Address(null,postalCode,deliveryAddress,deliveryName,phone,userId);
        addressDao.add(address);
    }
}

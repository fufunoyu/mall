package com.rhinoceros.mall.service.impl.service;
/* created at 10:51 AM 3/10/2018  */

import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.dao.dao.AddressDao;
import com.rhinoceros.mall.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public Address findById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public Address addDeliveryAddress(Address address) {
        return null;
    }

    /**
     * 根据用户id找到地址列表
     * @param id
     * @return
     */
    @Override
    public List<Address> findByUserId(Long id) {
        return addressDao.findByUserId(id);
    }
}

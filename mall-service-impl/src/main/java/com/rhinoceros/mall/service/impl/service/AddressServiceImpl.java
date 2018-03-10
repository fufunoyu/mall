package com.rhinoceros.mall.service.impl.service;
/* created at 10:51 AM 3/10/2018  */

import com.rhinoceros.mall.core.pojo.Address;
import com.rhinoceros.mall.dao.dao.AddressDao;
import com.rhinoceros.mall.service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public Address findById(Long id) {
        return addressDao.findById(id);
    }
}

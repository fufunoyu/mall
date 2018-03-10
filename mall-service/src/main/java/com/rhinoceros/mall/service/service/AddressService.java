package com.rhinoceros.mall.service.service;
/* created at 10:49 AM 3/10/2018  */

import com.rhinoceros.mall.core.pojo.Address;

public interface AddressService {

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Address findById(Long id);
}

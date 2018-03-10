package com.rhinoceros.mall.dao.dao;
/* created at 2:28 PM 3/6/2018  */

import com.rhinoceros.mall.core.po.Address;

public interface AddressDao {

    /**
     * 增加地址
     * @param address
     * @return
     */
    int add(Address address);
}

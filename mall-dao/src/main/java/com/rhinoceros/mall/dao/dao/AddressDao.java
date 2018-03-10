package com.rhinoceros.mall.dao.dao;
/* created at 2:28 PM 3/6/2018  */

import com.rhinoceros.mall.core.pojo.Address;

public interface AddressDao {

    /**
     * 增加地址
     * @param address
     * @return
     */
    int add(Address address);

    /**
     * 根据id查找信息
     * @param id
     * @return
     */
    Address findById(Long id);


}

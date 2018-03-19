package com.rhinoceros.mall.service.service;
/* created at 10:49 AM 3/10/2018  */

import com.rhinoceros.mall.core.po.Address;


import java.util.List;

public interface AddressService {

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Address findById(Long id);

    /**
     * 根据用户ID查找
     * @param userId
     * @return
     */
    List<Address> findByUserId(Long userId);

    /**
     * 更新地址中不为null的字段
     * @param address
     * @return
     */
    Address updateSelectionById(Address address);

    /**
     * 删除地址
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 添加
     * @param address
     * @return
     */
    Address add(Address address);
}

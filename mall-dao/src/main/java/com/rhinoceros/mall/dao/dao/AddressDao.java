package com.rhinoceros.mall.dao.dao;
/* created at 2:28 PM 3/6/2018  */

import com.rhinoceros.mall.core.po.Address;

import java.util.List;

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

    /**
     * 根据用户id显示地址信息
     * @param id
     * @return
     */
    List<Address> findByUserId(Long id);
    /**
     * 更新地址中不为null的字段
     *
     * @param address
     * @return
     */
    int updateSelectionById(Address address);

    /**
     * 删除地址
     *
     * @param id
     */
    void deleteById(Long id);

}

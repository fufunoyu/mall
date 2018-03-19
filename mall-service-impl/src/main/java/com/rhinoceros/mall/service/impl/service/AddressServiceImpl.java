package com.rhinoceros.mall.service.impl.service;
/* created at 10:51 AM 3/10/2018  */

import com.rhinoceros.mall.core.po.Address;
import com.rhinoceros.mall.dao.dao.AddressDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.List;

@Service
@Slf4j
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

    /***
     * 根据用户ID获取地址
     * @param userId
     * @return
     */
    @Override
    public List<Address> findByUserId(Long userId){
        if(userId == null){
            log.info("用户id不能为空");
            throw new ParameterIsNullException("用户id不能为空");
        }
        List<Address> addressList = addressDao.findByUserId(userId);
        if (addressList == null){
            log.info("不存在该用户对应的地址");
            throw new EntityNotExistException("不存在该用户对应的地址");
        }
        return addressList;
    }

    /**
     * 更新地址中不为null的字段
     * @param address
     * @return
     */
    @Override
    @Transactional
    public Address updateSelectionById(Address address){
        Long addressId = address.getId();
        if (addressId == null){
            log.info("地址id不能为空");
            throw new ParameterIsNullException("地址id不能为空");
        }
        Address old = addressDao.findById(addressId);
        if (old == null){
            log.info("地址不存在");
            throw new EntityNotExistException("地址不存在");
        }
        addressDao.updateSelectionById(address);
        return addressDao.findById(addressId);
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Integer deleteById(Long id){
        if (id == null){
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        addressDao.deleteById(id);
        return 1;
    }

    /**
     * 添加
     * @param address
     * @return
     */
    @Transactional
    public Address add(Address address){
        addressDao.add(address);
        return address;
    }
}

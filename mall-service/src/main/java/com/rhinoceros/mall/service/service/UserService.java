package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.pojo.User;

import java.util.List;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    List<User> findAll();

    /**
     * 注册方法
     * @param userDto
     */
    void register(RegisterUserDto userDto);
}

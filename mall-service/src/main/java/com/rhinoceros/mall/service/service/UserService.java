package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.pojo.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param userDto
     */
    void register(RegisterUserDto userDto);

    /**
     * 用户登录
     *
     * @param userDto
     * @return
     */
    User login(LoginUserDto userDto);
}

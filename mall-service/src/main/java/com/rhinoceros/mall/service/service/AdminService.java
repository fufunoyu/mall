package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.Admin;

/**
 * 用户业务逻辑接口
 */
public interface AdminService {

    /**
     * 用户登录
     *
     * @param userDto
     * @return
     */
    Admin login(LoginUserDto userDto);
}

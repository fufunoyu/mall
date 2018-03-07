package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.dto.ResetPasswordDto;
import com.rhinoceros.mall.core.dto.RetrievePasswordDto;
import com.rhinoceros.mall.core.pojo.User;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

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

    /**
     * 找回密码登陆提示邮箱是否已经注册
     * @param retrievePasswordDto
     */
    void retrievePassword(RetrievePasswordDto retrievePasswordDto);

    /**
     * 重置密码
     * @param resetPasswordDto
     */
    void resetPassword(ResetPasswordDto resetPasswordDto);
}

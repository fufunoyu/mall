package com.rhinoceros.mall.service.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.dto.ResetPasswordDto;
import com.rhinoceros.mall.core.dto.RetrievePasswordDto;
import com.rhinoceros.mall.core.po.User;

import java.io.InputStream;
import java.util.List;

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
     * 根据id获取用户
     *
     * @param userId
     * @return
     */
    User findById(Long userId);


    /**
     * 发送重置密码邮件
     *
     * @param retrievePasswordDto
     * @param redirectUrl         邮件跳转的url
     */
    void sendResetPasswordEmail(RetrievePasswordDto retrievePasswordDto, String redirectUrl);

    /**
     * 重置密码
     *
     * @param resetPasswordDto
     */
    void resetPassword(ResetPasswordDto resetPasswordDto);

    /**
     * 修改用户资料
     * @param user
     * @return
     */
    User updateSelectionById(User user);

    /**
     * 上传文件
     * @param is
     * @param filePath
     * @param fileName
     * @return
     */
    String upload(InputStream is, String filePath, String fileName);
}

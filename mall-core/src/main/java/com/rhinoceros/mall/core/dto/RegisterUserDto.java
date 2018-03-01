package com.rhinoceros.mall.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 注册用户实体类
 */
@Getter
@Setter
public class RegisterUserDto {
    //用户名
    private String username;
    //登陆密码
    private String password;
    //确认登陆密码
    private String rePassword;

    /**
     * 获取用户名
     * @return
     */
    public String getUsername(){
        return username;
    }

    /**
     * 获取登陆密码
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * 获取确认登陆密码
     * @return
     */
    public String getRePassword() {
        return rePassword;
    }

    /**
     * 设置登陆密码
     * @return
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 设置确认登陆密码
     * @return
     */
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    /**
     * 设置用户名
     * @return
     */
    public void setUsername(String username) {
        this.username = username;
    }
}

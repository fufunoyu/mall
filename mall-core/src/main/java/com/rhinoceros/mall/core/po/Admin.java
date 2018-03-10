package com.rhinoceros.mall.core.po;

import lombok.Data;
/**
 * 管理员实体类
 */
@Data
public class Admin {
    /**
     * 管理员id
     */
    private long id;
    /**
     * 管理员名称
     */
    private String username;
    /**
     * 管理员密码
     */
    private String password;

}
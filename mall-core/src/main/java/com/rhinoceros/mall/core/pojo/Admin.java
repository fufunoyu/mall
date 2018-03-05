package com.rhinoceros.mall.core.pojo;

import lombok.Data;
import java.util.Date;

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
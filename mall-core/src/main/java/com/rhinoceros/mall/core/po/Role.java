package com.rhinoceros.mall.core.po;

import lombok.Data;

/**
 * 角色实体
 */
@Data
public class Role {
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 创建时间
     */
    private String createAt;

}

package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.po.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    Admin findById(@Param("id")Long id);
}
package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.po.User;

public interface UserDao {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 添加用户
     *
     * @param user
     * @return 改变的数据库行数
     */
    int add(User user);

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 通过写入的邮箱在数据库中查找
     * @param email
     * @return
     */
    User findByEmail(String email);
}
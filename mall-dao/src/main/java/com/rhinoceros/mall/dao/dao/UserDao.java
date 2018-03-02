package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.User;

public interface UserDao {

    //通过用户名在数据库中查找
    User findByUsername(String username);

    //添加用户
    int add(User user);

    //通过写入的邮箱在数据库中查找
    User findByEmail(String email);
}
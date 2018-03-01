package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.User;

public interface UserDao {

    User findByUsername(String username);

    int add(User user);
}
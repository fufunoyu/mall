package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> select();

    User findByUsername(String username);

    int add(User user);
}
package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.User;
import org.apache.ibatis.annotations.Param;

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
     * @param id
     * @return
     */
    User findById(@Param("id")Long id);

    //修改用户信息
    int update(User user);

    //通过写入的邮箱在数据库中查找
    User findByEmail(String email);
}
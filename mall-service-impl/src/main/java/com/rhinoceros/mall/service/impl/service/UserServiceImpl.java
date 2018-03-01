package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.dto.UserDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.select();
    }

    /**
     * 根据用户名实例化用户对象
     * @param userDto
     *
     * @return user
     */
    public User login(UserDto userDto) {
        //根据用户名从dao中查询用户信息
        User user = userDao.getByUserName(userDto.getUsername());
        //判断用户是否存在,不存在则返回null
        if(user==null){
            return null;
        }
        /**
         *  如果存在，比较密码是否匹配，如果不匹配，则返回null/
         如果匹配，则返回user信息
         */
        if(user.getPassword().equals(userDto.getPassword())){
            return user;
        }
        return null;

    }
}

package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.PsaawordNotMatchException;
import com.rhinoceros.mall.service.impl.exception.UserNotFoundException;
import com.rhinoceros.mall.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public User login(LoginUserDto userDto) {
        //根据用户名从dao中查询用户信息
        User user = userDao.getByUserName(userDto.getUsername());
        //判断用户是否存在,不存在则返回null
            if(user==null){
                log.info("用户不存在");
               throw new UserNotFoundException("用户不存在");
            }
        /**
         *  用户存在，比较密码是否匹配，如果不匹配，则抛出异常
         */
        if(!user.getPassword().equals(userDto.getPassword())){
            log.info("密码输入错误");
            throw new PsaawordNotMatchException("密码输入错误");
        }
        //返回用户信息
        return user;

    }
}

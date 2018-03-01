package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.enumeration.Gender;
import com.rhinoceros.mall.core.enumeration.UserStatus;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.UserExistException;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.select();
    }

    /**
     * 注册实现方法
     * @param userDto
     */
    public void register(RegisterUserDto userDto) {
        //设置变量获取注册时输入的用户名
        String username = userDto.getUsername();

        //通过输入的用户名查询数据库
        User user = userDao.findByUsername(username);
        //如果用户已存在，程序抛出异常
        if(user != null){
            throw new UserExistException();
        }
        //创建用户
        User u = new User();

        //录入用户名
        u.setUsername(username);
        //录入登陆密码
        u.setPassword(userDto.getPassword());
        //初始邮件设置为空
        u.setEmail(null);
        //注册时昵称默认使用用户名
        u.setNickname(userDto.getUsername());
        //初始性别初始为空
        u.setGender(Gender.UNKOWN);
        //初始电话为空
        u.setTelephone(null);
        //初始生日为空
        u.setBirthday(null);
        //初始头像为空
        u.setAvatar(null);
        //记录注册时间
        u.setCreateAt(new Date());
        //上次登陆时间为空
        u.setLastLoginAt(null);
        //上次登陆ip地址为空
        u.setLastLoginIp(null);
        //设置用户状态为激活
        u.setStatus(UserStatus.ACTIVATED);
        //将用户信息录入数据库
        userDao.add(u);
    }

}

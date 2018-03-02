package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.dto.RegisterUserDto;
import com.rhinoceros.mall.core.enumeration.Gender;
import com.rhinoceros.mall.core.enumeration.UserStatus;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.PsaawordNotMatchException;
import com.rhinoceros.mall.service.impl.exception.UserHasFoundException;
import com.rhinoceros.mall.service.impl.exception.UserNotFoundException;
import com.rhinoceros.mall.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户注册，注册失败抛出{@link com.rhinoceros.mall.service.impl.exception.UserException}
     *
     * @param userDto
     */
    @Transactional
    public void register(RegisterUserDto userDto) {
        //设置变量获取注册时输入的用户名
        String username = userDto.getUsername();

        //通过输入的用户名查询数据库
        User user = userDao.findByUsername(username);
        //如果用户已存在，程序抛出异常
        if (user != null) {
            log.info("用户已经存在");
            throw new UserHasFoundException("用户已经存在");
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
        u.setGender(Gender.UNKNOWN);
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


    /**
     * 用户登录，登录失败抛出{@link com.rhinoceros.mall.service.impl.exception.UserException}
     *
     * @param userDto
     * @return user 登录成功返回的用户信息
     */
    @Transactional
    public User login(LoginUserDto userDto) {
        //根据用户名从dao中查询用户信息
        User user = userDao.findByUsername(userDto.getUsername());
        //判断用户是否存在,不存在则返回null
        if (user == null) {
            log.info("用户不存在");
            throw new UserNotFoundException("用户不存在");
        }
        // 用户存在，比较密码是否匹配，如果不匹配，则抛出异常
        if (!user.getPassword().equals(userDto.getPassword())) {
            log.info("密码输入错误");
            throw new PsaawordNotMatchException("密码输入错误");
        }
        //返回用户信息
        return user;
    }
}

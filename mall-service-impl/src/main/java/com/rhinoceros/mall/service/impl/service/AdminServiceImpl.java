package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.po.Admin;
import com.rhinoceros.mall.dao.dao.AdminDao;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.user.PsaawordNotMatchException;
import com.rhinoceros.mall.service.impl.exception.user.UserNotFoundException;
import com.rhinoceros.mall.service.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 用户登录，登录失败抛出{@link com.rhinoceros.mall.service.impl.exception.user.UserException}
     *
     * @param userDto
     * @return admin 登录成功返回的管理员信息
     */
    @Transactional
    public Admin login(LoginUserDto userDto) {
        //设置变量获取注册时输入的用户名
        String username = userDto.getUsername();
        if (username == null) {
            log.info("用户名不能为null");
            throw new ParameterIsNullException("用户名不能为null");
        }
        //根据用户名从dao中查询用户信息
        Admin admin = adminDao.findByUsername(userDto.getUsername());
        //判断用户是否存在,不存在则返回null
        if (admin == null) {
            log.info("管理员不存在");
            throw new UserNotFoundException("管理员不存在");
        }
        // 用户存在，比较密码是否匹配，如果不匹配，则抛出异常
        if (!admin.getPassword().equals(userDto.getPassword())) {
            log.info("密码输入错误");
            throw new PsaawordNotMatchException("密码输入错误");
        }
        //返回用户信息
        return admin;
    }
}

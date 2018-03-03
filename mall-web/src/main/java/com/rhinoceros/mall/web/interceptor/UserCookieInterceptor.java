package com.rhinoceros.mall.web.interceptor;

import com.rhinoceros.mall.core.dto.LoginUserDto;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.service.impl.exception.UserException;
import com.rhinoceros.mall.service.service.UserService;
import com.rhinoceros.mall.web.controller.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class UserCookieInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //session中没有信息，说明用户从未登录过，直接返回true进行登录
        if (session.getAttribute(LoginController.USERNAME) != null) {
            return true;
        }
        //获取cookie信息
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        //获取到cookie中的用户名和密码
        for (Cookie c : cookies) {
            if (LoginController.USERNAME_COOKIE.equals(c.getName())) {
                username = c.getValue();
            } else if (LoginController.USEPASSWORD_COOKIE.equals(c.getName())) {
                password = c.getValue();
            }
        }
        //判断用户名和密码是否为空，为空则表示未登录，直接返回true进行登录
        if (username == null || password == null) {
            return true;
        }
        /**
         * cookie中的用户信息与数据库用户信息对比
         */
        LoginUserDto dto = new LoginUserDto();
        dto.setUsername(username);
        dto.setPassword(password);
        try {
            User user = userService.login(dto);
            //把用户信息放在session里面
            request.getSession().setAttribute(LoginController.USERNAME, user);
        }catch (UserException e){
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return true;
    }
}

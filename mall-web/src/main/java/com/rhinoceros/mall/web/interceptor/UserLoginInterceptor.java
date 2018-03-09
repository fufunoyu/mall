package com.rhinoceros.mall.web.interceptor;
import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.service.service.UserService;
import com.rhinoceros.mall.web.controller.LoginController;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 登录拦截器
 */
@Slf4j
public class UserLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    private String[] methods = {
            "com.rhinoceros.mall.web.controller.LoginController.login",
            "com.rhinoceros.mall.web.controller.RegisterController.registerSubmit",
            "com.rhinoceros.mall.web.controller.IndexController.index",
            "com.rhinoceros.mall.web.controller.ProductDetailsController.index"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String method = handlerMethod.getMethod().getName();
        String className = handlerMethod.getMethod().getDeclaringClass().getName();
        String classMethodName = className.concat("."+method);
        //判断是否走进Controller的方法，走进去就不用拦截
        for(int i=0;i<methods.length;i++) {
            if (classMethodName.equals(methods[i])) {
                return true;
            }
        }
        //session中没有信息，说明用户从未登录过，直接返回true进行登录
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        if (session.getAttribute(ConstantValue.CURRENT_USER) == null) {
            response.sendRedirect("/login?"+ConstantValue.CALLBACK_URL+"="+requestURI+"?"+request.getQueryString());

            return false;
        }
        return true;
    }
}

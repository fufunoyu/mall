package com.rhinoceros.mall.admin.interceptor;

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.po.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
@Slf4j
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    private String[] allowUrls = {
            "/login",
            "/loginSubmit"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI().replace(request.getContextPath(), "");
        for (String allowUrl : allowUrls) {
            if (allowUrl.equals(url)) {
                return true;
            }
        }
        Object object = request.getSession().getAttribute(ConstantValue.CURRENT_USER);
        if (object == null || !(object instanceof Admin)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}

package com.rhinoceros.mall.web.interceptor;

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
@Slf4j
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 不进行拦截的方法
     */
    private String[] methods = {
            "com.rhinoceros.mall.web.controller.LoginController.login",
            "com.rhinoceros.mall.web.controller.LoginController.loginSubmit",
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
        String classMethodName = className.concat("." + method);
        //判断是否走进Controller的方法，走进去就不用拦截
        for (int i = 0; i < methods.length; i++) {
            if (classMethodName.equals(methods[i])) {
                return true;
            }
        }
        //session中没有信息，说明用户从未登录过，直接返回true进行登录
        HttpSession session = request.getSession();
        String path = request.getRequestURI().replace(request.getContextPath(), "");
        if (session.getAttribute(ConstantValue.CURRENT_USER) == null) {
            StringBuffer sb = new StringBuffer();
            sb.append(request.getContextPath())
                    .append("/login?")
                    .append(ConstantValue.CALLBACK_URL).append("=")
                    .append(path);
            if (request.getQueryString() != null) {
                sb.append("?").append(request.getQueryString());
            }
            response.sendRedirect(sb.toString());
            return false;
        }
        return true;
    }
}

package com.rhinoceros.mall.web.support.web.aop;

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.web.support.web.exception.AuthenticationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 16:54
 */
@Component
@Aspect
public class AuthenticationAop {

    @Pointcut("@annotation(com.rhinoceros.mall.web.support.web.annotation.Authentication)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void before(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取session
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        if (user == null) {
            throw new AuthenticationException("用户未登录");
        }
        joinPoint.proceed();
    }

}

package com.rhinoceros.mall.web.support.web.annotation;

import java.lang.annotation.*;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 17:09
 * 权限注解，对需要登陆的方法进行是否登陆校验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Authentication {
}

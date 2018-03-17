package com.rhinoceros.mall.web.support.web.annotation;

import java.lang.annotation.*;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/06 08:35
 * 分页注解，
 * 对需要进行分页或者排序的controller的方法参数上添加此注解，
 * 并且参数类型定义为{@link com.rhinoceros.mall.core.query.PageQuery}
 * 系统将会自动绑定请求的参数到PageQuery中
 * 请求参数的格式为：page=?&size=?&sort=field1,DESC&sort=field2,ASC&sort=field3,asc&field4,desc
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageDefault {

    /**
     * 默认的请求页
     *
     * @return
     */
    int page() default 1;

    /**
     * 默认的每页显示数据数
     *
     * @return
     */
    int size() default 10;

    /**
     * 默认分页方式
     *
     * @return
     */
    String[] sort() default {};

    /**
     * 分页参数是否必须有，默认必须有
     *
     * @return
     */
    boolean required() default true;
}

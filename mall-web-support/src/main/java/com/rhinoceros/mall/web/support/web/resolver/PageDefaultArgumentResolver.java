package com.rhinoceros.mall.web.support.web.resolver;

import com.rhinoceros.mall.core.query.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/06 09:58
 * 处理{@link com.rhinoceros.mall.web.support.web.annotation.PageDefault}注解参数的绑定
 */
public class PageDefaultArgumentResolver implements HandlerMethodArgumentResolver {


    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";
    public static final String SORT_PARAM = "sort";

    /**
     * 判断参数是否是PageQuery类型，且是否有PageDefault注解
     *
     * @param methodParameter
     * @return
     */
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasMethodAnnotation(PageDefault.class) && methodParameter.getParameterType().isAssignableFrom(PageQuery.class);
    }


    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        // 获取请求参数，并进行url解码
        String pageStr = URLDecoder.decode(nativeWebRequest.getParameter(PAGE_PARAM), "UTF-8");
        String sizeStr = URLDecoder.decode(nativeWebRequest.getParameter(SIZE_PARAM), "UTF-8");
        String[] sortStrArr = nativeWebRequest.getParameterValues(SIZE_PARAM);

        Integer page = null;
        Integer size = null;
        List<Order> orders = new LinkedList<Order>();

        for (int i = 0; i < sortStrArr.length; i++) {
            sortStrArr[i] = URLDecoder.decode(sortStrArr[i], "UTF-8");
        }

        if (webDataBinderFactory != null) {
            //绑定page size参数
            WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, methodParameter.getParameterName());
            page = binder.convertIfNecessary(pageStr, Integer.class);
            size = binder.convertIfNecessary(sizeStr, Integer.class);

            // 判断是否可以为空，如果不能，使用注解中的默认值
            PageDefault annotation = methodParameter.getParameterAnnotation(PageDefault.class);
            if (!annotation.required()) {
                if (page == null) {
                    page = annotation.page();
                }
                if (size == null) {
                    size = annotation.size();
                }
            }
        }

        // 绑定排序参数
        for (String sortStr : sortStrArr) {
            String[] split = sortStr.split(",");
            if (split.length != 2) {
                continue;
            }
            Order.Direction direction = null;
            if (split[1].toUpperCase().equals(Order.Direction.ASC.name())) {
                direction = Order.Direction.ASC;
            } else if (split[1].toUpperCase().equals(Order.Direction.DESC.name())) {
                direction = Order.Direction.DESC;
            }
            if (direction != null) {
                orders.add(new Order(split[0], direction));
            }
        }

        return new PageQuery(page, size, orders);
    }
}

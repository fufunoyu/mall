package com.rhinoceros.mall.web.support.web.resolver;

import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.net.URLDecoder;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/06 09:58
 * 处理{@link com.rhinoceros.mall.web.support.web.annotation.PageDefault}注解参数的绑定
 */
public class PageDefaultArgumentResolover implements HandlerMethodArgumentResolver {


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
        String[] sortStrs = nativeWebRequest.getParameterValues(SIZE_PARAM);

        for (int i = 0; i < sortStrs.length; i++) {
            sortStrs[i] = URLDecoder.decode(sortStrs[i], "UTF-8");
        }

        if (webDataBinderFactory != null) {
            WebDataBinder binder = webDataBinderFactory.createBinder(nativeWebRequest, null, methodParameter.getParameterName());
            Integer page = binder.convertIfNecessary(pageStr, Integer.class);
            Integer size = binder.convertIfNecessary(sizeStr, Integer.class);

        }
        return null;

    }
}

package com.rhinoceros.mall.service.impl.exception.common;

import com.rhinoceros.mall.service.impl.exception.BaseException;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 12:27
 * 参数不能为null异常
 */
public class ParameterIsNullException extends BaseException {

    public ParameterIsNullException(String msg) {
        super(msg);
    }
}

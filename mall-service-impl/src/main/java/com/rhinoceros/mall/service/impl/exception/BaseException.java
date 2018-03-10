package com.rhinoceros.mall.service.impl.exception;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 09:08
 * 基础异常类
 */
abstract public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String msg) {
        super(msg);
    }
}

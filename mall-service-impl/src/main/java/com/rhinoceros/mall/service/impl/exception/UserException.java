package com.rhinoceros.mall.service.impl.exception;

/**
 * 异常处理
 */
public abstract class UserException extends RuntimeException {

    public UserException(){}
    public UserException(String msg) {
        super(msg);
    }
}

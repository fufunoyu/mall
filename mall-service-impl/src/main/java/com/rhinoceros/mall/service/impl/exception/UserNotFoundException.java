package com.rhinoceros.mall.service.impl.exception;

/**
 * 用户名不存在异常
 */
public class UserNotFoundException extends UserException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}

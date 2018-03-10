package com.rhinoceros.mall.service.impl.exception.user;

import com.rhinoceros.mall.service.impl.exception.BaseException;

/**
 * 异常处理
 */
public abstract class UserException extends BaseException {

    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
    }
}

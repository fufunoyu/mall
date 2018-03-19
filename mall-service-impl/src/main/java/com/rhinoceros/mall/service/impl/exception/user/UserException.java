package com.rhinoceros.mall.service.impl.exception.user;

import com.rhinoceros.mall.service.impl.exception.BaseServiceException;

/**
 * 异常处理
 */
public abstract class UserException extends BaseServiceException {

    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
    }
}

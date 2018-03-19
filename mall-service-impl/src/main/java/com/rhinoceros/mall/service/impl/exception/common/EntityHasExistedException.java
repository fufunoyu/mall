package com.rhinoceros.mall.service.impl.exception.common;

import com.rhinoceros.mall.service.impl.exception.BaseException;

/**
 * 实体已存在异常
 */
public class EntityHasExistedException extends BaseException {

    public EntityHasExistedException(String msg) {
        super(msg);
    }
}

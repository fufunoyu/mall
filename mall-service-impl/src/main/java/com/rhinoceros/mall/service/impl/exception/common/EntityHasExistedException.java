package com.rhinoceros.mall.service.impl.exception.common;

import com.rhinoceros.mall.service.impl.exception.BaseServiceException;

/**
 * 实体已存在异常
 */
public class EntityHasExistedException extends BaseServiceException {

    public EntityHasExistedException(String msg) {
        super(msg);
    }
}

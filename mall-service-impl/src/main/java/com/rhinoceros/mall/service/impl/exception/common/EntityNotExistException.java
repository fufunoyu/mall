package com.rhinoceros.mall.service.impl.exception.common;

import com.rhinoceros.mall.service.impl.exception.BaseServiceException;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 09:10
 * <p>
 * 实体不存在异常
 */
public class EntityNotExistException extends BaseServiceException {

    public EntityNotExistException(String msg) {
        super(msg);
    }
}

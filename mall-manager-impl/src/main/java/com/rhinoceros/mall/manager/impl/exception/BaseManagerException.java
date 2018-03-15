package com.rhinoceros.mall.manager.impl.exception;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/12 20:56
 * <p>
 * manager层基本异常类
 */
public class BaseManagerException extends RuntimeException {
    public BaseManagerException(String msg) {
        super(msg);
    }
}

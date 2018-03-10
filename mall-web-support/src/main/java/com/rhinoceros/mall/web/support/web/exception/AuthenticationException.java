package com.rhinoceros.mall.web.support.web.exception;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 17:17
 * 权限异常
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String msg) {
        super(msg);
    }
}

package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.web.support.web.exception.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/10 17:26
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AuthenticationException.class})
    public String handler(AuthenticationException e) {
        return "redirect:/index";
    }
}

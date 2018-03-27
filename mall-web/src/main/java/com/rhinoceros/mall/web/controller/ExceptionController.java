package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.service.impl.exception.BaseServiceException;
import com.rhinoceros.mall.web.support.web.exception.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Rhys Xia
 * 2018/03/10 17:26
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AuthenticationException.class})
    public String authenticationExceptionHandler(AuthenticationException e) {
        return "redirect:/login";
    }


    @ExceptionHandler({BaseServiceException.class})
    public String serviceExceptionHandler(BaseServiceException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error/error";
    }

}

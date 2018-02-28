package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/index", "/"})
    public ModelAndView test() {

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}

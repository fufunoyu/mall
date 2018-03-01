package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.vo.CategoryVo;
import com.rhinoceros.mall.service.service.CategoryService;
import com.rhinoceros.mall.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping({"/index", "/"})
    public ModelAndView index() {
        List<CategoryVo> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}

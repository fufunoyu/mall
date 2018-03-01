package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping({"/index", "/"})
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        // 首页轮播图
        List<String> urls = new LinkedList<String>();
        urls.add("/static/img/lunbo/1.jpg");
        urls.add("/static/img/lunbo/2.jpg");
        urls.add("/static/img/lunbo/3.jpg");
        urls.add("/static/img/lunbo/4.jpg");
        model.addAttribute("images",urls);
        return "index";
    }
}

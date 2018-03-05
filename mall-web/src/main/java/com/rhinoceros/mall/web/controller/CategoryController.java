package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/05 09:57
 */
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category")
    public String list(@RequestParam("cid") Long cid,
                       @RequestParam(value = "page", required = false, defaultValue = "1") Long page) {


        return "category";
    }
}

package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建商品控制器
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    //定义要调用的逻辑业务对象
    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/list")
    public List<Category> getList(@RequestParam(value = "id", required = false) Long id) {
        List<Category> categories;
        if (id == null) {
            categories = categoryService.findRootCategories();
        } else {
            categories = categoryService.findByParentId(id);
        }

        return categories;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Category add(Category category) {
        return categoryService.add(category);
    }
}

package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexSlider;
import com.rhinoceros.mall.service.service.CategoryService;
import com.rhinoceros.mall.service.service.IndexProductService;
import com.rhinoceros.mall.service.service.IndexSliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IndexSliderService indexSliderService;

    @Autowired
    private IndexProductService indexProductService;

    @RequestMapping({"/index", "/"})
    public String index(Model model, HttpServletRequest request) {
        //分类
        List<Category> categories = categoryService.findChildrenById(null);
        model.addAttribute("categories", categories);
        // 首页轮播图
        List<IndexSlider> sliders = indexSliderService.findAll();
        model.addAttribute("sliders", sliders);

        //首页展示的商品
        List<CategoryWithProducts> categoryWithProducts = indexProductService.findAll();
        model.addAttribute("categoryWithProducts", categoryWithProducts);

        return "index";
    }
}

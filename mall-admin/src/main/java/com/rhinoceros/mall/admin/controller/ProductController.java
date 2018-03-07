package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.service.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    //定义要调用的逻辑业务对象
    @Autowired
    private ProductService productService;

    /**
     * 定义方法找到商品并转换为json格式返回
     *
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List productList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, @RequestParam(value="categoryId") Long categoryId) {
        PageQuery pageQuery = new PageQuery(page, 10);
        List<Product> productList = productService.findByCategoryId(categoryId,pageQuery);
        return productList;
    }

    /**
     * 显示商品
     * @return
     */
    @RequestMapping
    public String showProduct() {
        return "include/product";
    }

}

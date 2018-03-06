package com.rhinoceros.mall.admin.controller;

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
    @RequestMapping("/productList")
    public List productList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        PageQuery pageQuery = new PageQuery(page, 10);
        List<Product> productList = productService.findProduct(pageQuery);
        return productList;
    }

    @RequestMapping("/product")
    public String showProduct() {
        return "include/product";
    }

}

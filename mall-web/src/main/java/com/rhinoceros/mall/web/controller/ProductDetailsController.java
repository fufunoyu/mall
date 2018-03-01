package com.rhinoceros.mall.web.controller;
/* created at 8:44 AM 3/1/2018  */

import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.impl.service.ProductServiceImpl;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品详情页跳转
 */
@Controller
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    /**
     * 跳转到pid对应的商品详情页
     * @param id pid号码
     * @return 商品详情页的url
     */
    @RequestMapping({"/product"})
    public ModelAndView GoProduct(
            @RequestParam("pid") Long id
    ) {
        ProductVo productVo = productService.findProductVoById(id);
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("productVo", productVo);
        return modelAndView;
    }



}

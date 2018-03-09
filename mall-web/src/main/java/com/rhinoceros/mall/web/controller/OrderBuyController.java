package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.impl.service.ProductServiceImpl;
import com.rhinoceros.mall.service.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单确认页面处理
 */

@Controller
public class OrderBuyController {
    /**
     * 展示订单确认页面
     * @return
     */
    @Autowired
    ProductService productService;

    @RequestMapping("/add/order")
    public String showOrderConfirm(@Param("pid")Long pid,@Param("num")Integer num ,HttpServletRequest request,Model model){
        ProductVo productVo = productService.findProductVoById(pid);
        productVo.setNum(num);
        model.addAttribute("productVo", productVo);
        return "forecreateOrder";
    }
}

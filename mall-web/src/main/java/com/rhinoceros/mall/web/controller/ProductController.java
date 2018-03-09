package com.rhinoceros.mall.web.controller;
/* created at 8:44 AM 3/1/2018  */

import com.rhinoceros.mall.core.vo.CommentVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.impl.service.ProductDescriptionServiceImpl;
import com.rhinoceros.mall.service.service.CommentService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品详情页跳转
 */
@Controller
public class ProductController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDescriptionServiceImpl productDescriptionService;

    /**
     * 商品详情页展示
     * @param id
     * @param page
     * @param model
     * @return
     */
    @RequestMapping({"/product"})
    public String index(
            @RequestParam("pid") Long id,
            @RequestParam(value = "page", required = false) Integer page,
            Model model
    ) {

        model.addAttribute("isComment", page != null);
        if (page == null) {
            page = 1;
        }
        ProductVo productVo = new ProductVo(productService.findById(id));
        //获取商品详情
        productVo.setProductDescription(productDescriptionService.findByProductId(id));
        model.addAttribute("productVo", productVo);

        List<CommentVo> comments = commentService.findByProductId(id, page, 10);
        model.addAttribute("comments", comments);
        model.addAttribute("nowPage", page);
        return "product";
    }






}

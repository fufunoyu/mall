/*
package com.rhinoceros.mall.web.controller;
*/
/* created at 8:25 PM 3/1/2018  *//*


import com.rhinoceros.mall.core.pojo.Comment;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.CommentService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @RequestMapping({"/comment"})
    public ModelAndView GoProduct(
            @RequestParam("pid") Long id,
            @RequestParam("page") Integer n
    ) {
        ProductVo productVo = productService.findProductVoById(id);
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("productVo", productVo);

        List<Comment> comments = commentService.findByProductId(id, n, 10);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("nowPage", n);
        return modelAndView;
    }

}
*/

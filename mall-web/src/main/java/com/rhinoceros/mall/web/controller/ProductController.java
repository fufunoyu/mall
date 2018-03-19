package com.rhinoceros.mall.web.controller;
/* created at 8:44 AM 3/1/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.CommentVo;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.CommentService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.service.service.UserService;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

/**
 * 商品详情页跳转
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /**
     * 商品详情页展示
     *
     * @param id
     * @param page
     * @param model
     * @return
     */
    @RequestMapping
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
        model.addAttribute("productVo", productVo);

        List<Comment> comments = commentService.findByProductId(id, new PageQuery(page, 10,new Order("createAt", Order.Direction.DESC)));

        List<CommentVo> commentVos = new LinkedList<>();
        for (Comment comment : comments) {
            CommentVo vo = new CommentVo();
            vo.setComment(comment);
            vo.setUser(userService.findById(comment.getUserId()));
            commentVos.add(vo);
        }

        model.addAttribute("comments", commentVos);
        model.addAttribute("nowPage", page);
        return "product";
    }

    /**
     * 查询商品
     *
     * @param query
     * @param pageQuery
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("query") String query, @PageDefault(required = false) PageQuery pageQuery, Model model) {

        List<Product> products = productService.query(query, pageQuery);

        List<ProductVo> vos = new LinkedList<>();

        for (Product product : products) {
            ProductVo productVo = new ProductVo(product);
            vos.add(productVo);
        }

        Long total = productService.countQuery(query);

        model.addAttribute("total", total);
        model.addAttribute("query", query);
        model.addAttribute("page", pageQuery);
        model.addAttribute("productList", vos);
        return "searchResult";
    }
}

package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.CategoryService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.web.support.web.annotation.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhys Xia
 * <p>
 * 2018/03/05 09:57
 */
@Controller
public class CategoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取商品分类下的商品列表
     *
     * @param cid
     * @param pageQuery
     * @param model
     * @return
     */
    @RequestMapping("/category")
    public String list(@RequestParam("cid") Long cid,
                       @PageDefault(required = false) PageQuery pageQuery,
                       Model model) {
        List<Product> products = productService.findDeepByCategoryId(cid, pageQuery);

        List<ProductVo> productVos = new LinkedList<>();
        for (Product p : products) {
            ProductVo productVo = new ProductVo(p);
            productVos.add(productVo);
        }

        model.addAttribute("products", productVos);

        /**
         * 通过id查询分类
         */
        Category category = categoryService.findById(cid);
        model.addAttribute("category", category);
        return "category";
    }

    /**
     * 根据id获取子分类
     * @param id
     * @return
     */
    @RequestMapping("/category/list.json")
    @ResponseBody
    public List<Category> getById(@RequestParam("parentId") Long id) {
        return categoryService.findChildrenById(id);
    }
}

package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.Order;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.CategoryWithProductsVo;
import com.rhinoceros.mall.service.service.CategoryService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @RequestMapping({"/index", "/"})
    public String index(Model model, HttpServletRequest request) {
        List<Category> categories = categoryService.findChildrenById(null);
        model.addAttribute("categories", categories);
        // 首页轮播图
        String path = request.getContextPath();
        List<String> urls = new LinkedList<String>();
        urls.add(path + "/static/img/lunbo/1.jpg");
        urls.add(path + "/static/img/lunbo/2.jpg");
        urls.add(path + "/static/img/lunbo/3.jpg");
        urls.add(path + "/static/img/lunbo/4.jpg");
        model.addAttribute("images", urls);

        //获取每种根分类下的销量最好的5种产品
        //获取根分类
        List<CategoryWithProductsVo> categoryWithProductList = new LinkedList<CategoryWithProductsVo>();
        for (Category category : categories) {
            if (category.getParentId() == null) {
                CategoryWithProductsVo vo = new CategoryWithProductsVo();
                vo.setCategory(category);
                //查询分类下销量最好的产品
                PageQuery pageQuery = new PageQuery(1, 5, new Order("saleNum", Order.Direction.DESC));
                List<Product> products = productService.findDeepByCategoryId(category.getId(), pageQuery);
                vo.setProducts(products);
                categoryWithProductList.add(vo);
            }
        }
        model.addAttribute("categoryWithProductList", categoryWithProductList);
        return "index";
    }
}

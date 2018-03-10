package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexProduct;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.vo.CategoryWithProductsVo;
import com.rhinoceros.mall.service.service.IndexProductService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * 创建首页分类展示商品控制器
 */
@Controller
@RequestMapping("/home")
public class IndexProductController {
    //定义要调用的逻辑业务对象
    @Autowired
    private IndexProductService indexProductService;
    @Autowired
    private ProductService productService;

    @RequestMapping()
    public String showCategoryList() {
        return "include/categorylist";
    }

    /**
     * 获取所有首页分类展示商品列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.json")
    public List<CategoryWithProductsVo> getCategoryList(){
        List<CategoryWithProducts> categoryWithProductsList = indexProductService.findAll();
        List<CategoryWithProductsVo> categoryWithProductsVo = new LinkedList<CategoryWithProductsVo>();

        for (CategoryWithProducts categoryWithProducts : categoryWithProductsList) {
            CategoryWithProductsVo vo = new CategoryWithProductsVo();
            Category category = new Category();
            category.setId(categoryWithProducts.getId());
            category.setName(categoryWithProducts.getName());
            category.setParentId(categoryWithProducts.getParentId());


            //初始化分类
            vo.setCategory(category);
            //初始化分类下的展示商品
            vo.setProducts(categoryWithProducts.getProducts());
            categoryWithProductsVo.add(vo);
        }
        return categoryWithProductsVo;
    }

    /**
     * 添加商品
     * @param productIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/addproduct.json")
    public List<Product> addProduct(@RequestParam("ids[]") List<Long> productIds){
        List<Product> products = new LinkedList<Product>();
        for (Long productId : productIds) {
            IndexProduct indexProduct = new IndexProduct();
            indexProduct.setProductId(productId);
            indexProductService.add(indexProduct);
            Product product = productService.findById(productId);
            products.add(product);
        }
        return products;
    }

    /**
     *  删除分类下的商品
     * @param productIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteproduct.json")
    public String deleteProduct(@RequestParam("ids[]") List<Long> productIds){
        for (Long productId : productIds){
            indexProductService.deleteById(productId);
        }
        return "{\"result\":\"success\"}";
    }

}

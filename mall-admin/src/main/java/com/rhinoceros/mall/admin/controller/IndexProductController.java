package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.core.pojo.CategoryWithProducts;
import com.rhinoceros.mall.core.pojo.IndexProduct;
import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.vo.CategoryWithProductsVo;
import com.rhinoceros.mall.service.service.IndexProductService;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/list")
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
     */
    @RequestMapping("/addproduct")
    public void addProduct(List<Long> productIds){
        for (Long productId : productIds) {
            IndexProduct indexProduct = new IndexProduct();
            indexProduct.setProductId(productId);
            indexProductService.add(indexProduct);
        }
    }

    /**
     * 删除分类下的商品
     * @param productIds
     */
    @RequestMapping("/deleteproduct")
    public void deleteProduct(List<Long> productIds){
        for (Long productId : productIds){
            indexProductService.deleteById(productId);
        }
    }

    /**
     * 查找商品列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/chooselist")
    public List<Product> getProductList(){
        List<Product> products = productService.findAll();
        return products;
    }

}

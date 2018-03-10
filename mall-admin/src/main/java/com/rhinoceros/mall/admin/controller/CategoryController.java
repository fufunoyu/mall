package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.service.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    //定义要调用的逻辑业务对象
    @Autowired
    private CategoryService categoryService;

    /**
     * 通过id查询商品分类
     *
     * @param id
     * @return
     */
    @RequestMapping
    @ResponseBody
    public Category findById(@RequestParam(value = "id") Long id) {
        return categoryService.findById(id);
    }

    /**
     * 获取所有商品分类列表的方法
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Category> getList(@RequestParam(value = "id", required = false) Long id) {
        return categoryService.findChildrenById(id);
    }

    /**
     * 插入分类
     *
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Category add(Category category) {
        return categoryService.add(category);
    }

    /**
     * 删除分类
     *
     * @param category
     */
    @ResponseBody
    @RequestMapping("/delete")
    public void delete(Category category) {
        categoryService.deleteById(category.getId());
    }

    @ResponseBody
    @RequestMapping("/update")
    public Category updateSelection(Category category) {
        return categoryService.updateSelection(category);
    }
}

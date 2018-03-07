package com.rhinoceros.mall.admin.controller;

import com.rhinoceros.mall.core.dto.TreeNodeDto;
import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
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
     * 获取所有商品分类列表的方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<TreeNodeDto> getList(@RequestParam(value = "id", required = false) Long id) {
        List<Category> categories;
        if (id == null) {
            categories = categoryService.findRootCategories();
        } else {
            categories = categoryService.findByParentId(id);
        }
        List<TreeNodeDto> dtoList = new LinkedList<TreeNodeDto>();
        for (Category category : categories) {
            TreeNodeDto dto = new TreeNodeDto();
            dto.setId(category.getId());
            dto.setText(category.getName());
            dto.setState("closed");
            dto.setParentId(category.getParentId());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Category add(Category category){
        return categoryService.add(category);
    }

//    @ResponseBody
//    @RequestMapping("/delete")
//    public Category delete(Category category){
//        return categoryService.fcategory);
//    }

}

package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.core.vo.CategoryVo;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<CategoryVo> findAll() {
        List<Category> categories = categoryDao.findAll();

        List<CategoryVo> categoryVos = new LinkedList<CategoryVo>();

        List<CategoryVo> levelCategories = new LinkedList<>();
        List<CategoryVo> temp = new LinkedList<>();

        Iterator<Category> iterator = categories.iterator();

        while (iterator.hasNext()) {
            Category category = iterator.next();
            if (category.getParentId() == null) {
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setCategory(category);
                categoryVos.add(categoryVo);
                levelCategories.add(categoryVo);
                iterator.remove();
            }
        }

        iterator = categories.iterator();
        while (iterator.hasNext() && !levelCategories.isEmpty()) {
            Category next = iterator.next();
            for (CategoryVo categoryVo : levelCategories) {
                if (categoryVo.getCategory().getId().equals(next.getParentId())) {
                    CategoryVo vo = new CategoryVo();
                    categoryVo.setCategory(next);
                    categoryVo.getChildren().add(vo);
                    temp.add(vo);
                    iterator.remove();
                    break;
                }
            }
            if (!temp.isEmpty()) {
                levelCategories = temp;
                temp = new LinkedList<>();
            }
        }

        return categoryVos;
    }
}

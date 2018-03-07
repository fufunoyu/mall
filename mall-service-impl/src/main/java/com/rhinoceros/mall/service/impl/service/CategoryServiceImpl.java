package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findRootCategories() {
        return categoryDao.findRootCategories();
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryDao.findByParentId(parentId);
    }

    @Override
    public Category add(Category category) {
        categoryDao.add(category);
        return category;
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryDao.findById(categoryId);
    }
}

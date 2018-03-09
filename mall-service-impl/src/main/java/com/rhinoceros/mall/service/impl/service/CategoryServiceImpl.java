package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.Category;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
    public List<Category> findChildrenById(Long id) {
        return categoryDao.findChildrenById(id);
    }

    @Override
    public List<Category> findChildrenAndBelowById(Long id) {
        List<Category> list = this.findChildrenById(id);
        Iterator<Category> iterator = list.iterator();
        while (iterator.hasNext()) {
            list.addAll(this.findChildrenById(iterator.next().getId()));
        }
        return list;
    }

    @Override
    public Category add(Category category) {
        categoryDao.add(category);
        return category;
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }
}

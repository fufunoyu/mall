package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
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

    @Transactional
    @Override
    public Category add(Category category) {
        Category parent = categoryDao.findById(category.getParentId());
        if (parent == null) {
            log.info("不存在指定父分类");
            throw new EntityNotExistException("不存在指定父分类");
        }
        categoryDao.add(category);
        return category;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Category category = categoryDao.findById(id);
        if (category == null) {
            log.info("分类不存在");
            throw new EntityNotExistException("分类不存在");
        }
        categoryDao.deleteById(id);
    }

    @Transactional
    @Override
    public Category updateSelection(Category category) {
        Category old = categoryDao.findById(category.getId());
        if (old == null) {
            log.info("分类不存在");
            throw new EntityNotExistException("分类不存在");
        }
        if (category.getParentId() != null) {
            Category parent = categoryDao.findById(category.getParentId());
            if (parent == null) {
                log.info("父分类不存在");
                throw new EntityNotExistException("父分类不存在");
            }
        }
        categoryDao.updateSelectionById(category);
        return category;
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }
}

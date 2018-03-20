package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.Category;
import com.rhinoceros.mall.dao.dao.CategoryDao;
import com.rhinoceros.mall.service.impl.exception.category.CategoryHasFoundException;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
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

    /**
     * @param category
     * @return
     */
    @Transactional
    @Override
    public Category add(Category category) {
        //判断菜单栏名字是否重复
        Category category1 = findByCategoryName(category.getName());
        if (category1 != null) {
            log.info("该分类栏已存在");
            throw new CategoryHasFoundException("该分类栏已存在");
        }
        //如果parentId为null，则表示添加根分类，否则添加到指定分类下，会对parentId是否存在进行校验
        if (category.getParentId() != null) {
            Category parent = categoryDao.findById(category.getParentId());
            if (parent == null) {
                log.info("不存在指定id的父分类");
                throw new EntityNotExistException("不存在指定id的父分类");
            }
        }
        categoryDao.add(category);
        return category;
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
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
        if (category.getId() == null) {
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
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

    /**
     * 查找菜单栏名字
     * @param name
     * @return
     */
    public Category findByCategoryName(String name) {
        return categoryDao.findByCategoryName(name);
    }

}

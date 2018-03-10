package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexProduct;
import com.rhinoceros.mall.dao.dao.IndexProductDao;
import com.rhinoceros.mall.service.service.IndexProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndexProductServiceImpl implements IndexProductService{

    @Autowired
    private IndexProductDao indexProductDao;

    @Override
    public List<CategoryWithProducts> findAll() {
        return indexProductDao.findAll();
    }

    @Override
    public List<IndexProduct> findByCategoryId(Long categoryId) {
        return indexProductDao.findByCategoryId(categoryId);
    }

    @Transactional
    @Override
    public IndexProduct add(IndexProduct indexProduct) {
        indexProductDao.add(indexProduct);
        return indexProduct;
    }

    @Transactional
    @Override
    public void deleteById(Long indexProductId) {
        indexProductDao.deleteById(indexProductId);
    }
}

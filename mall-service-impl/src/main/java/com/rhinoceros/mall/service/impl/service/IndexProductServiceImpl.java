package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.CategoryWithProducts;
import com.rhinoceros.mall.core.pojo.IndexProduct;
import com.rhinoceros.mall.dao.dao.IndexProductDao;
import com.rhinoceros.mall.service.service.IndexProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public IndexProduct add(IndexProduct indexProduct) {
        indexProductDao.add(indexProduct);
        return indexProduct;
    }

    @Override
    public void deleteById(Long indexProductId) {
        indexProductDao.deleteById(indexProductId);
    }
}

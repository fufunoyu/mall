package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.CategoryWithProducts;
import com.rhinoceros.mall.core.po.IndexProduct;
import com.rhinoceros.mall.dao.dao.IndexProductDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.IndexProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class IndexProductServiceImpl implements IndexProductService {

    @Autowired
    private IndexProductDao indexProductDao;

    @Autowired
    private ProductDao productDao;

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
        Long productId = indexProduct.getProductId();
        if (productId == null) {
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        Product product = productDao.findById(productId);
        if (product == null) {
            log.info("商品不存在");
            throw new EntityNotExistException("商品不存在");
        }
        indexProductDao.add(indexProduct);
        return indexProduct;
    }

    @Transactional
    @Override
    public void deleteByProductId(Long productId) {
        IndexProduct indexProduct = indexProductDao.findByProductId(productId);
        if (indexProduct == null) {
            log.info("指定在首页展示的商品不存在");
            throw new EntityNotExistException("指定在首页展示的商品不存在");
        }
        indexProductDao.deleteById(indexProduct.getId());
    }
}

package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findByCategoryId(Long categoryId, PageQuery pageQuery) {
        return productDao.findByCategoryId(categoryId, pageQuery);
    }
}

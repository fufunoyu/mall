package com.rhinoceros.mall.service.impl.service;


import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.manager.manager.ProductManager;
import com.rhinoceros.mall.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Rhys Xia
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductManager productManager;

    /**
     * 根据商品id获取商品信息，并封装成商品展示信息对象
     *
     * @param id 商品id号
     * @return 商品信息展示对象
     */
    @Override
    public Product findById(Long id) {
        return productManager.findById(id);
    }


    @Override
    public List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery) {
        return productManager.findDeepByCategoryId(categoryId, pageQuery);
    }

    @Override
    public List<Product> query(String query, PageQuery pageQuery) {
        return productManager.query(query, pageQuery);
    }

    @Override
    public Long countQuery(String query) {
        return productManager.countQuery(query);
    }

    @Override
    public Long countDeepByCategoryId(Long categoryId) {

        return productManager.countDeepByCategoryId(categoryId);
    }

}

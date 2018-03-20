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


    /**
     * 通过分类id查找当前及其子节点下所有商品
     * @param categoryId 分类id
     * @param pageQuery  分页条件
     * @return
     */
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

    /**
     * 通过分类id查询当前及其子节点的数量
     * @param categoryId
     * @return
     */
    @Override
    public Long countDeepByCategoryId(Long categoryId) {
        return productManager.countDeepByCategoryId(categoryId);
    }

    /**
     * 通过id删除商品
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        productManager.deleteById(id);
    }

    /**
     * 通过id修改商品
     * @param product
     */
    @Override
    @Transactional
    public void updateSelectionById(Product product) {
        productManager.updateSelectionById(product);
    }

    /**
     * 通过id增添商品
     * @param product
     */
    @Override
    public void addSelectionById(Product product) {
        productManager.add(product);
    }

}

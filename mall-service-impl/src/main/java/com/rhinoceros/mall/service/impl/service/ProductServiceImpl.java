package com.rhinoceros.mall.service.impl.service;


import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.dao.dao.DescriptionDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.manager.manager.ProductManager;
import com.rhinoceros.mall.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rhys Xia
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private DescriptionDao descriptionDao;

    @Autowired
    private ProductManager productManager;

    /**
     * 根据商品id获取商品信息，并封装成商品展示信息对象
     *
     * @param id 商品id号
     * @return 商品信息展示对象
     */
    @Override
    public ProductVo findProductVoById(Long id) {
        Product product = productDao.findById(id);
        ProductVo productVo = new ProductVo();
        //获取商品图片url数组
        productVo.setImagesUrls(product.getImageUrls().split(Product.IMAGE_SEPARATION));
        productVo.setFirstImageUrl(productVo.getImagesUrls()[0]);
        //获取商品详情
        productVo.setDescription(descriptionDao.findByProductId(product.getId()));
        productVo.setProduct(product);
        return productVo;

    }

    @Override
    public List<Product> findDeepByCategoryId(Long categoryId, PageQuery pageQuery) {
        return productManager.findDeepByCategoryId(categoryId, pageQuery);
    }

    @Override
    public List<Product> findAll(){
        return productDao.findProductNoPQ();
    }

    /**
     * 找寻商品的方法
     *
     * @param pageQuery
     * @return
     */
    @Override
    public List<Product> findAll(PageQuery pageQuery) {
        return productDao.findAll(pageQuery);
    }
}

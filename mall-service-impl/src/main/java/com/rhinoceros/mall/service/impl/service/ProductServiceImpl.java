package com.rhinoceros.mall.service.impl.service;

/* created at 4:12 PM 2/28/2018  */

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.vo.ProductVo;


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

    /**
     * 根据商品id获取商品信息，并封装成商品展示信息对象
     * @param id 商品id号
     * @return 商品信息展示对象
     */
    public ProductVo findProductVoById(Long id) {
        Product product = productDao.findById(id);
        ProductVo productVo = new ProductVo();
        //获取商品图片url数组
        productVo.setImagesUrls(product.getImageUrls().split(Product.IMAGE_SEPARATION));
        productVo.setFirstImageUrls(productVo.getImagesUrls()[0]);
        //获取商品详情图片的url数组
        productVo.setDescriptionImagesUrls(product.getDescriptionImageUrls().split(Product.IMAGE_SEPARATION));
        productVo.setProduct(product);
        return productVo;

    }
    @Override
    public List<Product> findByCategoryId(Long categoryId, PageQuery pageQuery) {
        return productDao.findByCategoryId(categoryId, pageQuery);
    }
}

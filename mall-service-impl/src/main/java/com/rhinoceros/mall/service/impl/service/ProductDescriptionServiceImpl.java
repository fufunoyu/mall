package com.rhinoceros.mall.service.impl.service;
/* created at 3:30 PM 3/9/2018  */

import com.rhinoceros.mall.core.pojo.ProductDescription;
import com.rhinoceros.mall.dao.dao.ProductDescriptionDao;
import com.rhinoceros.mall.service.service.ProductDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

    @Autowired
    private ProductDescriptionDao productDescriptionDao;

    @Override
    public ProductDescription findByProductId(Long id) {
        return productDescriptionDao.findByProductId(id);
    }
}

package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findByProductId(Long id);
}

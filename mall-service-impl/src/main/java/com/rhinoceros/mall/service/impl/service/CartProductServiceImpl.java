package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.pojo.CartProduct;
import com.rhinoceros.mall.dao.dao.CartProductDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.service.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartProductServiceImpl implements CartProductService{
    @Autowired
    private CartProductDao cartProductDao;
    private ProductDao productDao;
    /**
     * 获取用户的购物车中的商品信息
     * @param userId
     * @return
     */
    @Override
    public List<CartProduct> findByUserId(Long userId) {
        return cartProductDao.findByUserId(userId);
    }

    /**
     * 删除购物车中商品
     * @param id
     */
    @Override
    public int deleteByid(Long id) {
        return cartProductDao.deleteById(id);
    }

    /**
     * 修改购物车中商品数量
     * @param cartProduct
     */
    @Override
    public Integer updateSelectionById(CartProduct cartProduct) {
        return cartProductDao.updateSelectionById(cartProduct);
    }
}


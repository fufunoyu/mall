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
    public void deleteById(Long id) {
        cartProductDao.deleteById(id);
    }

    @Override
    public void addProduct(Long productId, Long userId, Integer productNum) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(productId);
        cartProduct.setUserId(userId);
        cartProduct.setProductNum(productNum);
        CartProduct cartProduct1 = cartProductDao.findByUserIdAndProductId(userId, productId);
        if (cartProduct1 != null) {
            cartProduct1.setProductNum(cartProduct1.getProductNum() + productNum);
            cartProductDao.updateById(cartProduct1);
        } else {
            cartProductDao.add(cartProduct);
        }
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


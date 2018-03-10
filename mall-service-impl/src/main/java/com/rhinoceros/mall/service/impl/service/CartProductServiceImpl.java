package com.rhinoceros.mall.service.impl.service;

import com.rhinoceros.mall.core.po.CartProduct;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.dao.dao.CartProductDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.CartProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CartProductServiceImpl implements CartProductService {
    @Autowired
    private CartProductDao cartProductDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    /**
     * 获取用户的购物车中的商品信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<CartProduct> findByUserId(Long userId) {
        return cartProductDao.findByUserId(userId);
    }

    /**
     * 删除购物车中商品
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.info("id不能为空");
            throw new ParameterIsNullException("id不能为空");
        }
        CartProduct cartProduct = cartProductDao.findById(id);
        if (cartProduct == null) {
            log.info("指定id的商品购车不存在");
            throw new EntityNotExistException("指定id的商品购车不存在");
        }
        cartProductDao.deleteById(id);
    }

    /**
     * 向购物车中增加商品
     *
     * @param productId
     * @param userId
     * @param productNum
     */
    @Transactional
    @Override
    public void add(Long productId, Long userId, Integer productNum) {
        if (productId == null) {
            log.info("商品id不能为空");
            throw new ParameterIsNullException("商品id不能为空");
        }
        Product product = productDao.findById(productId);
        if (product == null) {
            log.info("商品不存在");
            throw new EntityNotExistException("商品不存在");
        }
        if (userId == null) {
            log.info("用户id不能为空");
            throw new ParameterIsNullException("用户id不能为空");
        }
        User user = userDao.findById(userId);
        if (user == null) {
            log.info("用户不存在");
            throw new EntityNotExistException("用户不存在");
        }

        CartProduct cartProduct = cartProductDao.findByUserIdAndProductId(userId, productId);
        //判断购物车中是否存在该商品,如果存在,直接将商品的数量增加
        if (cartProduct != null) {
            cartProduct.setProductNum(cartProduct.getProductNum() + productNum);
            cartProductDao.updateSelectionById(cartProduct);
        } else {
            //如果不存在，新建一个购物车，保存相关数据
            cartProduct = new CartProduct();
            cartProduct.setProductId(productId);
            cartProduct.setProductNum(productNum);
            cartProductDao.add(cartProduct);
        }
    }

    /**
     * 根据id更新购物车中不为null的字段
     *
     * @param cartProduct
     */
    @Transactional
    @Override
    public Integer updateSelectionById(CartProduct cartProduct) {
        Long cartId = cartProduct.getId();
        if (cartId == null) {
            log.info("购物车id不能为空");
            throw new ParameterIsNullException("购物车id不能为空");
        }
        CartProduct old = cartProductDao.findById(cartId);
        if (old == null) {
            log.info("购物车不存在");
            throw new EntityNotExistException("购物车不存在");
        }
        if (cartProduct.getProductId() != null) {
            Product product = productDao.findById(cartProduct.getProductId());
            if (product == null) {
                log.info("商品不存在");
                throw new EntityNotExistException("商品不存在");
            }
        }
        if (cartProduct.getUserId() != null) {
            User user = userDao.findById(cartProduct.getUserId());
            if (user == null) {
                log.info("用户不存在");
                throw new EntityNotExistException("用户不存在");
            }
        }
        return cartProductDao.updateSelectionById(cartProduct);
    }
}


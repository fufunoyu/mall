package com.rhinoceros.mall.service.impl.service;
/* created at 8:06 PM 3/1/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.CommentDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.service.CommentService;
import com.rhinoceros.mall.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;

    @Override
    public List<Comment> findByProductId(Long productId, PageQuery pageQuery) {
        return commentDao.findByProductId(productId, pageQuery);
    }

    @Transactional
    @Override
    public void add(Comment comment) {
        if (comment.getProductId() == null) {
            log.info("商品id不能为空");
            throw new ParameterIsNullException("商品id不能为空");
        }
        Product product = productDao.findById(comment.getProductId());
        if (product == null) {
            log.info("商品不存在");
            throw new EntityNotExistException("商品不存在");
        }
        if (comment.getUserId() == null) {
            log.info("用户id不能为空");
            throw new ParameterIsNullException("用户id不能为空");
        }
        User user = userDao.findById(comment.getUserId());
        if (user == null) {
            log.info("用户不存在");
            throw new EntityNotExistException("用户不存在");
        }
        Long oid = comment.getOrderId();
        if (oid == null) {
            log.info("订单id不能为空");
            throw new ParameterIsNullException("订单id不能为空");
        }
        if (orderService.findById(oid) == null) {
            log.info("订单不存在");
            throw new EntityNotExistException("订单不存在");
        }
        if (comment.getContent() == null||comment.getContent().trim().equals("")) {
            log.info("评论不能为空");
            throw new ParameterIsNullException("评论不能为空");
        }
        comment.setCreateAt(new Date());
        commentDao.add(comment);
    }
}

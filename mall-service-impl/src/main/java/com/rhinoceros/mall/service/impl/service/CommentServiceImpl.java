package com.rhinoceros.mall.service.impl.service;
/* created at 8:06 PM 3/1/2018  */

import com.rhinoceros.mall.core.enumeration.OrderStatus;
import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.po.Order;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.CommentDao;
import com.rhinoceros.mall.dao.dao.OrderDao;
import com.rhinoceros.mall.dao.dao.ProductDao;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.impl.exception.common.EntityNotExistException;
import com.rhinoceros.mall.service.impl.exception.common.ParameterIsNullException;
import com.rhinoceros.mall.service.impl.exception.order.OrderStatusException;
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
    @Autowired
    private OrderDao orderDao;

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
        if (comment.getContent() == null || comment.getContent().trim().equals("")) {
            log.info("评论不能为空");
            throw new ParameterIsNullException("评论不能为空");
        }
        Order order = orderDao.findById(oid);
        if (order == null) {
            log.info("订单不存在");
            throw new EntityNotExistException("订单不存在");
        }

        if(order.getStatus() != OrderStatus.WAIT_COMMENT){
            log.info("订单不是待评论状态");
            throw new OrderStatusException("订单不是待评论状态");
        }
        comment.setCreateAt(new Date());
        commentDao.add(comment);
        //增加总评论条数
        Long pid = comment.getProductId();
        Product product1 = new Product();
        Long commentNum = productDao.findById(pid).getCommentNum()+1;
        product1.setCommentNum(commentNum);
        product1.setId(pid);
        productDao.updateSelectionById(product1);
        //更改订单状态
        order.setStatus(OrderStatus.COMPLETED);
        orderService.updateSelectionById(order);
    }
}

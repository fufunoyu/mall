package com.rhinoceros.mall.service.impl.service;
/* created at 8:06 PM 3/1/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.dao.dao.CommentDao;
import com.rhinoceros.mall.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findByProductId(Long productId, PageQuery pageQuery) {
        return commentDao.findByProductId(productId, pageQuery);
    }

    @Override
    public List<Comment> findByUserId(Long userId, PageQuery pageQuery){
        return commentDao.findByUserId(userId,pageQuery);
    }
}

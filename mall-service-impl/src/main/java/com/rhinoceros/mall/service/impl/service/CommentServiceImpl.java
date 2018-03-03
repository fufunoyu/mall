package com.rhinoceros.mall.service.impl.service;
/* created at 8:06 PM 3/1/2018  */

import com.rhinoceros.mall.core.pojo.Comment;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.CommentVo;
import com.rhinoceros.mall.dao.dao.CommentDao;
import com.rhinoceros.mall.dao.dao.UserDao;
import com.rhinoceros.mall.service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<CommentVo> findByProductId(Long productId, Integer page, Integer size) {
        PageQuery pageQuery = new PageQuery(page, size);

        List<Comment> comments = commentDao.findByProductId(productId, pageQuery);

        List<CommentVo> vos = new LinkedList<CommentVo>();

        for (Comment comment : comments) {
            Long userId = comment.getUserId();
            User user = userDao.findById(userId);
            CommentVo commentVo = new CommentVo();
            commentVo.setComment(comment);
            commentVo.setUser(user);
            vos.add(commentVo);
        }
        return vos;
    }
}

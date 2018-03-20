package com.rhinoceros.mall.service.service;
/* created at 8:02 PM 3/1/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.query.PageQuery;

import java.util.List;

public interface CommentService {

    /**
     * 根据商品id查询一定数目的商品评价
     *
     * @param productId
     * @param pageQuery
     * @return
     */
    List<Comment> findByProductId(Long productId, PageQuery pageQuery);

    /**
     * 添加评论
     * @param comment
     */
    void add(Comment comment);

    /**
     * 根据用户id查询商品评价
     * @param userId
     * @param pageQuery
     * @return
     */
    List<Comment> findByUserId(Long userId, PageQuery pageQuery);

    /**
     * 查找对应用户id的评论总数
     * @param userId
     * @return
     */
    int commentNumByUserId(Long userId);
}

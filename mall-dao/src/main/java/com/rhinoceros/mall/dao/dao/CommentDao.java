package com.rhinoceros.mall.dao.dao;
/* created at 7:34 PM 3/1/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao {
    /**
     * 根据商品id查询一定数目的商品评价
     *
     * @param productId 商品id
     * @param pageQuery 分页对象
     * @return 商品评价的对象的列表
     */
    List<Comment> findByProductId(@Param("productId") Long productId, @Param("page") PageQuery pageQuery);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    int add(Comment comment);

    /**
     * 根据用户id查询商品评价
     * @param userId
     * @param pageQuery
     * @return
     */
    List<Comment> findByUserId(@Param("userId") Long userId, @Param("page") PageQuery pageQuery);

    /**
     * 查找对应用户id的评论总数
     * @param userId
     * @return
     */
    int commentNumByUserId(Long userId);
}

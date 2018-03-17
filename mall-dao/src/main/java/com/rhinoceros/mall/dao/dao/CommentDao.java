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
}

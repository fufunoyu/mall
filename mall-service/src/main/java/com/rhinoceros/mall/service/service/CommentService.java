package com.rhinoceros.mall.service.service;
/* created at 8:02 PM 3/1/2018  */

import com.rhinoceros.mall.core.vo.CommentVo;

import java.util.List;

public interface CommentService {

    /**
     * 根据商品id查询一定数目的商品评价
     *
     * @param productId 商品id
     * @param page      第几页
     * @param size      每页数目
     * @return 商品评价的对象的列表
     */
    List<CommentVo> findByProductId(Long productId, Integer page, Integer size);
}

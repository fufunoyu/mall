package com.rhinoceros.mall.core.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 商品的评论
 */
@Data
public class Comment {

    /**
     * id
     */
    private Long id;

    /**
     * 对应的商品id
     */
    private Long productId;

    /**
     * 对应订单id
     */
    private Long orderId;

    /**
     * 对应的用户id
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论日期
     */
    private Date createAt;

}

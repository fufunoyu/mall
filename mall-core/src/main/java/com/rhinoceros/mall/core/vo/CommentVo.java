package com.rhinoceros.mall.core.vo;
/* created at 8:43 AM 3/2/2018  */

import com.rhinoceros.mall.core.po.Comment;
import com.rhinoceros.mall.core.po.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo  {

    /**
     * 评论信息
     */
    private Comment comment;

    /**
     * 写评论的用户的信息
     */
    private User user;
}

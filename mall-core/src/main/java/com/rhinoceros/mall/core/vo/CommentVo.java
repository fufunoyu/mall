package com.rhinoceros.mall.core.vo;
/* created at 8:43 AM 3/2/2018  */

import com.rhinoceros.mall.core.pojo.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo extends Comment {

    /**
     * 此变量储存评论的用户姓名
     */
    private String userName;
}

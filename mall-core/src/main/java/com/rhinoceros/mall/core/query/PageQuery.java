package com.rhinoceros.mall.core.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分账分页查询条件
 */
@NoArgsConstructor
public class PageQuery {

    /**
     * 第几页，从1开始
     */
    @Getter
    @Setter
    private Integer page = 1;

    /**
     * 每页显示数
     */
    @Getter
    @Setter
    private Integer size = 10;


    /**
     * 偏移量
     */
    @Getter
    private Integer offset = (page - 1) * size;

    public PageQuery(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}

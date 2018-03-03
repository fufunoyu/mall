package com.rhinoceros.mall.core.query;

import lombok.Getter;

/**
 * 排序条件
 */
public class Order {

    /**
     * 排序字段
     */
    @Getter
    private String field;

    /**
     * 排序方式
     */
    @Getter
    private Direction direction;


    public Order(String field, Direction direction) {
        this.direction = direction;
        this.field = field;
    }

    /**
     * 排序方向
     */
    public enum Direction {
        /**
         * 降序
         */
        DESC,

        /**
         * 升序
         */
        ASC
    }
}

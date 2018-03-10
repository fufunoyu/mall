package com.rhinoceros.mall.core.vo;
/* created at 7:31 PM 3/6/2018  */


import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProductVo {

    /**
     * 商品展示信息对象
     */
    private ProductVo productVo;


    /**
     * 商品数目
     */
    private Integer num;
}

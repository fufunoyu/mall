package com.rhinoceros.mall.core.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DeliveryInfosVo {
    private List<DeliveryInfoVo>deliveryInfoVos;
    /**
     * 商品总量
     */
    private Long count;
}

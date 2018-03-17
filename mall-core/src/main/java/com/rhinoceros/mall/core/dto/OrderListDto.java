package com.rhinoceros.mall.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListDto {
    private List<OrderDto> orders;
}

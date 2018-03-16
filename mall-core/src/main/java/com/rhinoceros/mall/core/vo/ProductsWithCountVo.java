package com.rhinoceros.mall.core.vo;

import com.rhinoceros.mall.core.po.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 产品列表和产品数量
 */
@Data
public class ProductsWithCountVo {
    private List<Product> products;
    private Long count;
}

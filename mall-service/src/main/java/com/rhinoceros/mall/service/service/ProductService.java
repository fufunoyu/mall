package com.rhinoceros.mall.service.service;
/* created at 4:11 PM 2/28/2018  */


import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import com.rhinoceros.mall.core.vo.ProductVo;

import java.util.List;

/**
 * 查询商品信息
 */
public interface ProductService {
    /**
     * 根据id查询商品信息
     * @param id 商品id号
     * @return 商品信息
     */
    ProductVo findProductVoById(Long id);

    /**
     * 根据分类id和分页条件查找产品列表
     *
     * @param categoryId 分类id
     * @param pageQuery  分页条件
     * @return
     */
    List<Product> findByCategoryId(Long categoryId, PageQuery pageQuery);

    List<Product> findProduct(PageQuery pageQuery);

}

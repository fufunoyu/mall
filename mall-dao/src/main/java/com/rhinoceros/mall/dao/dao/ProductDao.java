package com.rhinoceros.mall.dao.dao;
/* created at 3:37 PM 2/28/2018  */

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductDao {
    /**
     * 根据id从数据库查找商品全部信息
     *
     * @param id 商品id
     * @return 商品对象
     */
    Product findById(@Param("id") Long id);

    /**
     * 查找出商品的全部信息
     *
     * @param pageQuery
     * @return
     */
    List<Product> findAll(@Param("page") PageQuery pageQuery);


    /**
     * 查找产品列表,无分类信息
     * @return
     */
    List<Product> findProductNoPQ();

    /**
     * 查找指定分类列表下直接的产品
     *
     * @param categoryIds
     * @param pageQuery
     * @return
     */
    List<Product> findByCategoryIdIn(@Param("categoryIds") List<Long> categoryIds, @Param("page") PageQuery pageQuery);
}

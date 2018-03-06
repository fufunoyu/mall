package com.rhinoceros.mall.dao.dao;
/* created at 3:37 PM 2/28/2018  */

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.pojo.User;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;


public interface ProductDao {
    /**
     * 根据id从数据库查找商品全部信息
     * @param id 商品id
     * @return 商品对象
     */
    Product findById(@Param("id") Long id);


    /**
     * 根据分类id和分页条件查找产品列表
     *
     * @param categoryId
     * @param pageQuery
     * @return
     */
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId, @Param("page") PageQuery pageQuery);

    /**
     * 查找出商品的全部信息
     * @param pageQuery
     * @return
     */
    List<Product> findProduct(@Param("page") PageQuery pageQuery);


}

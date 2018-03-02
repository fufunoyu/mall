package com.rhinoceros.mall.dao.dao;

import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.core.query.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 根据分类id和分页条件查找产品列表
     *
     * @param categoryId
     * @param pageQuery
     * @return
     */
    List<Product> findByRootCategoryId(@Param("categoryId") Long categoryId, @Param("page") PageQuery pageQuery);
}
